package io.github.bilcitytycoon;

public class MoneyHandler {
    private int income;
    private int expense;
    private int netIncome;

    public MoneyHandler() {
        this.income = 0;
        this.expense = 0;
        this.netIncome = 0;
    }
    public int getIncome(){
        return this.income;
    }

    //Called when a new source of income emerges
    public void updateIncome(int amount){
        this.income += amount;
        updateNetIncome();
    }

    public int getExpense(){
        return this.expense;
    }

    //Called when a new source of expense emerges
    public void updateExpense(int amount){
        this.expense += amount;
        updateNetIncome();
    }

    public int getNetIncome(){
        return this.netIncome;
    }

    //Called when net income is needed to be calculated with either an updated income or expense
    public void updateNetIncome(){
        this.netIncome = income - expense;
    }
}
