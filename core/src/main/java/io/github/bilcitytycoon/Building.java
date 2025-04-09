package io.github.bilcitytycoon;

public abstract class Building {
    protected String name;
    protected double buildCost;
    protected double bill;


    public Building(String name, double cost){
        this.name = name;
        this.buildCost = cost;
    }
    public abstract void getInfo();
}
