package com.sebaahs.builderview.src.model.domain;

public class Calculation {

    private String name;
    private float cost, amount;

    public Calculation(){    }

    public Calculation(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public float getCost() { return cost; }
    public float getAmount() { return amount; }

    public void setName(String name) {
        this.name = name;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }
    public void addAmount(float amount) {
        this.amount += amount;
    }
}
