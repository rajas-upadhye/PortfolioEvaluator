package com.Entity;

import com.Entity.Portfolio;

/**
 * Created by rajasupadhye on 3/17/17.
 */
public class Result {
    Portfolio portfolio;
    Double median;
    Double best;
    Double worst;

    public Result() {

    }

    public Result(Portfolio p, Double m, Double b, Double w) {
        portfolio = p;
        median = m;
        best = b;
        worst = w;
    }

    public Portfolio getPortfolio(){
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public Double getMedian() {
        return median;
    }

    public void setMedian(Double median) {
        this.median = median;
    }

    public Double getBest() {
        return best;
    }

    public void setBest(Double best) {
        this.best = best;
    }

    public Double getWorst() {
        return worst;
    }

    public void setWorst(Double worst) {
        this.worst = worst;
    }

    @Override
    public String toString(){
        return "Median : " + this.median + "\t 10% Best Case : " + this.getBest() + "\t 10% Worst Case : " + this.getWorst();
    }

}
