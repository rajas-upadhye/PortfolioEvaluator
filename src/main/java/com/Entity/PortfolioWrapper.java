package com.Entity;

/**
 * Created by rajasupadhye on 3/17/17.
 */
public class PortfolioWrapper {
    Portfolio portfolio ;
    Double investment;
    Double duration;
    Double inflation;

    public PortfolioWrapper(Portfolio p , Double investmentAmount , Double time, Double inflationRate){
        portfolio = p;
        investment = investmentAmount;
        duration = time;

        inflation = inflationRate;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    protected void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Double getInvestment() {
        return investment;
    }

    protected void setInvestment(Double investment) {
        this.investment = investment;
    }

    public Double getDuration() {
        return duration;
    }

    protected void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getInflation() {
        return inflation;
    }

    protected void setInflation(Double inflation) {
        this.inflation = inflation;
    }


}
