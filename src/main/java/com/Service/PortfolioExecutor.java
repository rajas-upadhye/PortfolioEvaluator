package com.Service;

import com.Entity.PortfolioWrapper;
import com.Entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rajasupadhye on 3/17/17.
 */
public class PortfolioExecutor {
    static Logger logger = LoggerFactory.getLogger(PortfolioExecutor.class);
    static final int SIMULATIONS = 10000;
    static final int THREADS = 500;
    static final int TASKS_PER_THREAD = SIMULATIONS/THREADS;
    static ConcurrentSkipListMap<Double,Double> simulationResult = null;

    Result result;

    public PortfolioExecutor(){
        result = new Result();
    }

    public Result runAnalysis(PortfolioWrapper portfolioWrapperParam){
        //logger.info("Run Analysis starting ...");
        simulationResult = new ConcurrentSkipListMap<Double, Double>();
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        Result result = new Result();
        final PortfolioWrapper portfolioWrapper= portfolioWrapperParam;

        if(portfolioWrapper!=null){
            for(int i = 0 ; i < THREADS ; i++){
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        //logger.info("Running simulation on " + Thread.currentThread().getName());
                        for(int j = 0 ; j < TASKS_PER_THREAD ; j++){
                            /*
                              1. Set the investment amount
                              2. Calculate a random number from -SD to +SD in % and then change it to amount by applying it to
                                 initial investment amount
                              3. Calculate inflation amount based on inflation %
                              4. Calculate the return amount based on return %
                              5. This year result = return amount + random number amount - inflation amount
                              6. Repeat by setting investment amount = result.
                             */

                            double investment = portfolioWrapper.getInvestment();
                            double riskPercent = portfolioWrapper.getPortfolio().getRiskRate();
                            double returnPercent = portfolioWrapper.getPortfolio().getReturnRate();
                            double inflationPercent = portfolioWrapper.getInflation();
                            double minVariationPercent = -1*riskPercent;

                            double variablesPercent = 0.0;
                            double variablesAmount = 0.0;
                            double inflationAmount = 0.0;
                            double expectedReturnAmount = 0.0;
                            double yearlyResult = 0.0;

                            for(int k = 0 ; k < portfolioWrapper.getDuration(); k++){
                                //logger.info("Calculating year : " + (k+1) + " on simulation : " + Thread.currentThread().getName());
                                /*
                                Here to drastically change the variablesAmount random weights can be used so that the
                                variable amount can impact drastically too.
                                */

                                variablesPercent = minVariationPercent + (riskPercent - minVariationPercent) * Math.random();
                                variablesAmount = (investment*variablesPercent)/100;

                                inflationAmount = (investment*inflationPercent)/100;
                                expectedReturnAmount = investment + (investment*returnPercent)/100;
                                yearlyResult = expectedReturnAmount + variablesAmount - inflationAmount;
                                investment = yearlyResult;
                            }

                            //this is after the entire duration is done . In our case its 20 years
                            //Now we save this final amount to our ConcurrentSkipListMap
                            simulationResult.put(investment,j*1.0);
                        }
                    }
                });
            }

            executorService.shutdown();
            while(!executorService.isTerminated()){
                //Wait for all threads to finish
                //logger.info("Service is still operating");
            }
            //logger.info("Service finished");
            //logger.info("Calculating the result ...");
            result = getResult(simulationResult);
            simulationResult.clear();
            return result;
        }
        return null;
    }

    public static Result getResult(ConcurrentSkipListMap<Double,Double> input){
        Result result = new Result();
        if(input!=null){
            int totalSimulations = input.size();
            int count = -1;
            //keeping percentiles simple by using direct int corresponding indices
            int worstMark = (int)Math.round(0.10*totalSimulations);
            int bestMark = (int)Math.round(0.90*totalSimulations);

            if(totalSimulations % 2 == 0) {
                //even sized
                Double prev = null;
                for (Map.Entry<Double, Double> entry : input.entrySet()) {
                   count++;
                   if (count == (totalSimulations / 2) - 1) {
                       prev = entry.getKey();
                   }else if (count == (totalSimulations / 2) && prev != null) {
                       result.setMedian((prev + (entry.getKey())) / 2);
                   }
                   //10th percentile -> worst
                   if(count == worstMark)
                       result.setWorst(entry.getKey());
                   //90th percentile -> best
                   if(count == bestMark)
                       result.setBest(entry.getKey());
                }
            }else{
                //odd sized
                for (Map.Entry<Double, Double> entry : input.entrySet()) {
                    count++;
                    if (count == (totalSimulations / 2)) {
                        result.setMedian(entry.getKey());
                    }
                    //10th percentile -> worst
                    if(count == worstMark)
                        result.setWorst(entry.getKey());
                    //90th percentile -> best
                    if(count == bestMark)
                        result.setBest(entry.getKey());
                }
            }
        }
        //logger.info("Done calculating the result ...");
        return result;
    }

    public static double getBest(ConcurrentSkipListMap<Double,Double> input){
        //calculate 10% best or 90th Percentile
        double result = 0.0;
        if(input != null){

        }
        return result;
    }

    public static double getWorst(ConcurrentSkipListMap<Double,Double> input){
        //calculate 10%  worst or 10th Percentile
        double result = 0.0;
        if(input != null){

        }
        return result;
    }
}

