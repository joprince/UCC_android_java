package com.example.lab_test_1;

import java.io.Serializable;

public class currency implements Serializable {
    private String name;
    private double rate;
    private double inverseRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getInverseRate() {
        return inverseRate;
    }

    public void setInverseRate(double inverseRate) {
        this.inverseRate = inverseRate;
    }

    public currency(String name, double rate, double inverseRate) {
        this.name = name;
        this.rate = rate;
        this.inverseRate = inverseRate;
    }

    public double convert(double amount, int direction){
        if (direction == -1){
            return amount*inverseRate;
        }

        else if (direction == 1) {
            return amount * rate;
        }
        else{
            throw new java.lang.Error("Invalid input!!");
        }
    }
}
