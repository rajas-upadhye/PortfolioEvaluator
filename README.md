# PortfolioEvaluator
Portfolio Analysis : This project is focused on basic portfolio analysis.

# Assumptions

Following are the assumptions for this project : 
  1. The Return and Risk rates for portfolio apply yearly.
  2. The yearly result becomes the investment for the next year.
  3. The variable amount accounts for anything unknown that happens during the duration of the portfolio. Currently this ranges within the Risk rate but we can provide weights to this random change to increase the impact.
  4. The inflation amount is calculated on the current investment(starting and then feedback yearly results) every year.
  5. Formula used for every year result calculation is as;
        Yearly result = Expected return amount + random variable amount - inflation amount
  6. The input is as mentioned in the project description and the output is displayed in the console when the jar runs.

# Understanding the code

  # Build : Maven is used to build this project . Command : mvn clean install
  
  # Run : The above builds the jar for this project . Run the jar with path to the target jar as  "/PortfolioEvaluator/target/portfolio-evaluator-jar-with-dependencies.jar"
  
  # Package and Class structure : 
  1. com.Entity : This package represents the entity based classes as ;
      a. Portfolio : Stores information directly related with portfolio (Name, Risk and Return).
      b. Portfolio : Designed to store information indirectly related to the specific Portfolio (Investment amount , Inflation rate, Duration in years). 
      c. Result : To Store the Result (Median , 10% Best and 10% Worst).
  2. com.Service : This package represents the service based classes as;
      a. PortfolioExecutor : This class is the core of the application . Here we the method runAnalysis runs the simulations (SIMULATIONS is defined as constant here). The idea is to take PortfolioWrapper object as input and to concurrently spawn threads. Initial 500 threads with 20 simulations each. Each simulation does the calculation for 20 years (this duration is property of the portfolio). Hence total time taken is O(SIMULATIONS).
  3. Evaluator : This is the driver class. It contains the main methods. Here we create Portfolio(s), PortfolioWrapper(s) and PortfolioExecutor(s) for different types of portfolios we want to analyze. 
  
