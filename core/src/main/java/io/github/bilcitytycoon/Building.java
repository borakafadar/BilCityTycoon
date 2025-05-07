package io.github.bilcitytycoon;

public abstract class Building {
    protected String name;
    protected int buildCost;
    protected int bill;


    public Building(String name, int cost, int bill){
        this.name = name;
        this.buildCost = cost;
        this.bill = bill;
    }

    public abstract String getInfo();

    public String getName(){
        return this.name;
    }

    public int getCost(){
        return this.buildCost;
    }

}
