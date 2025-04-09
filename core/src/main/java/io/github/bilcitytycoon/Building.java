package io.github.bilcitytycoon;

public abstract class Building {
    protected String name;
    protected double buildCost;


    public Building(String name, double cost){
        this.name = name;
        this.buildCost = cost;
    }

    public abstract void getInfo();

    public String getName(){
        return this.name;
    }
    
    public double getCost(){
        return this.buildCost;
    }

}
