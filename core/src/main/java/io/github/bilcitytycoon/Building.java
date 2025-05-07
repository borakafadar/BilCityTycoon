package io.github.bilcitytycoon;

public abstract class Building {
    protected String name;
    protected int buildCost;
    protected double bill;


    public Building(String name, int cost, double bill){
        this.name = name;
        this.buildCost = cost;
        this.bill = bill;
    }

    public abstract String getInfo();

    public String getName(){
        return this.name;
    }

    public double getCost(){
        return this.buildCost;
    }

}
