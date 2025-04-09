package io.github.bilcitytycoon;

public class OtherBuildings extends Building{
    private double income;

    public OtherBuildings(String name, double cost, double income) {
        super(name, cost);
        this.income = income;
    }

    public double getIncome() {
        return this.income;
    }

    public void getInfo(){
        //TODO
    }
}

