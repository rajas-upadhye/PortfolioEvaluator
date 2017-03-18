package com;

import com.Entity.Portfolio;
import com.Entity.PortfolioWrapper;
import com.Entity.Result;
import com.Service.PortfolioExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rajasupadhye on 3/17/17.
 */
public class Evaluator {
    public static void main (String args[]){
        Logger log = LoggerFactory.getLogger(Evaluator.class);
        log.info("Starting Portfolio Analysis at : " + System.currentTimeMillis()/1000);
        Portfolio aggressive = new Portfolio("Aggressive",9.4324,15.675);
        Portfolio conservative = new Portfolio("Conservative",6.189,6.3438);
        PortfolioWrapper aggressiveWrapper = new PortfolioWrapper(aggressive,100000.00,20.0,3.5);
        PortfolioWrapper conservativeWrapper = new PortfolioWrapper(conservative,100000.00,20.0,3.5);
        PortfolioExecutor aggressiveExecutor = new PortfolioExecutor();
        PortfolioExecutor conservativeExecutor = new PortfolioExecutor();

        //analyze aggressive
        log.info("Starting the Aggressive Portfolio analysis...");
        Result aggressivePerformance = aggressiveExecutor.runAnalysis(aggressiveWrapper);
        log.info(aggressivePerformance.toString());
        log.info("Done with the Aggressive Portfolio analysis...");
        //analyze conservative
        log.info("Starting the Conservative Portfolio analysis...");
        Result conservativePerformance = conservativeExecutor.runAnalysis(conservativeWrapper);
        log.info(conservativePerformance.toString());
        log.info("Done with the Conservative Portfolio analysis...");
        log.info("Finished Portfolio Analysis at : " + System.currentTimeMillis()/1000);
    }

}
