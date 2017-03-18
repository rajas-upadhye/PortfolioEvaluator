package com.Entity;

/**
 * Created by rajasupadhye on 3/17/17.
 */
public class Portfolio {
    private String name ;
    private Double returnRate;
    private Double riskRate;

    public Portfolio(String portfolioName , Double returnPercent , Double risk ){
        name = portfolioName;
        returnRate = returnPercent;
        riskRate = risk;
    }


    public Double getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(Double returnRate) {
        this.returnRate = returnRate;
    }

    public Double getRiskRate() {
        return riskRate;
    }

    public void setRiskRate(Double riskRate) {
        this.riskRate = riskRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
