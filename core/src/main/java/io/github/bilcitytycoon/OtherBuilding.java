package io.github.bilcitytycoon;

public class OtherBuilding extends Building{
    private double income;

    public OtherBuilding(String name, double cost, double income) {
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

