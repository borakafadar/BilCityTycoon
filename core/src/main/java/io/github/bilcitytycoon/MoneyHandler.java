package io.github.bilcitytycoon;

/**
 * Handles all money-related operations for the player, including income, expenses,
 * balance tracking, and processing financial actions such as spending and collecting.
 */
public class MoneyHandler {

    /**
     * Total income from all sources.
     */
    private int income;

    /**
     * Total recurring expenses (e.g., building bills).
     */
    private int expense;

    /**
     * Net income calculated as income - expense.
     */
    private int netIncome;

    /**
     * Current coin balance of the player.
     */
    private int balance;

    /**
     * Creates a new MoneyHandler with a starting balance.
     *
     * @param startingBalance Initial money for the player.
     */
    public MoneyHandler(int startingBalance) {
        this.income = 0;
        this.expense = 0;
        this.netIncome = 0;
        this.balance = startingBalance;
    }

    /**
     * Default constructor (used during deserialization or testing).
     * libGDX's JSON class requires a constructor without any parameters, so this is just a placeholder.
     */
    public MoneyHandler() {}

    public int getIncome() {
        return this.income;
    }

    /**
     * Called when a new source of income emerges.
     *
     * @param amount Amount to add to total income.
     */
    public void updateIncome(int amount) {
        this.income += amount;
        updateNetIncome();
    }

    public int getExpense() {
        return this.expense;
    }

    /**
     * Called when a new source of expense emerges.
     *
     * @param amount Amount to add to total expenses.
     */
    public void updateExpense(int amount) {
        this.expense += amount;
        updateNetIncome();
    }

    public int getNetIncome() {
        return this.netIncome;
    }

    /**
     * Recalculates net income as (income - expense).
     * Called after any income or expense change.
     */
    public void updateNetIncome() {
        this.netIncome = income - expense;
    }

    /**
     * Adds money to the player's balance.
     *
     * @param amount Amount to be added.
     */
    public void earn(int amount) {
        this.balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Tries to subtract money from the player's balance.
     *
     * @param amount Amount to spend.
     * @return true if successful; false if not enough money.
     */
    public boolean spend(int amount) {
        if (balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("Not enough money!");
            return false;
        }
    }

    /**
     * Applies the net income to the balance.
     * Typically called once per in-game day.
     */
    public void processDay() {
        balance += netIncome;
    }

    /**
     * Collects all monthly income from the player's buildings and adds it to their coin balance.
     *
     * @param player The player owning the buildings.
     */
    public void collectMonthlyIncome(Player player) {
        int totalIncome = 0;
        for (Building building : player.buildings) {
            totalIncome += building.getIncome();
        }
        player.setCoin(player.getCoin() + totalIncome);
    }
}
