package io.github.bilcitytycoon;

public class Faculty extends Building {

    private double income;
    private double bill;

    public Faculty(String name, double cost, double bill, double income) {
        super(name, cost);
        this.income = income;
        this.bill = bill;
    }

    public double getBill() {
        return this.bill;
    }

    public double getIncome() {
        return this.income;
    }

    @Override
    public void getInfo() {
        //TODO
    }
}
