package io.github.bilcitytycoon;

import com.badlogic.gdx.utils.Json;

/*
 * Abstract class representing a building in the game.
 * It contains common properties and methods for all buildings.
 * Specific building types (e.g., Faculty, Rectorate) will extend this class.
 */
public abstract class Building{
    public String name;
    public String info;
    public int buildCost;
    public int bill;
    public int constructionTime;
    public Upgrade currentUpgrade;

    // Constructor for the Building class
    // Initializes the building's name, cost, and bill.
    public Building(String name, int cost, int bill){
        this.info = "No additional information";
        this.constructionTime = 0;
        this.name = name;
        this.buildCost = cost;
        this.bill = bill;
        this.currentUpgrade = null; // Initially set to null
    }

    //An abstract method to get the building's information.
    public abstract String getInfo();

    // Getter methods for the building's properties
    public String getName(){
        return this.name;
    }

    public int getCost(){
        return this.buildCost;
    }

    public int getBill(){
        return this.bill;
    }
    public int getConstructionTime(){
        return this.constructionTime;
    }
    //Setter methods for the building's properties
    public void setConstructionTime(int time){
        this.constructionTime = time;
    }
    public void setInfo(String info){
        this.info = info;
    }

    public void setUpgrade(Upgrade upgrade) {
        this.currentUpgrade = upgrade;
         if (upgrade != null) {
            upgrade.applyUpgrade(); // Mark the upgrade as applied
            applyUpgradeEffects(upgrade); // Apply the effects of the upgrade
        }
    }

    public Upgrade getUpgrade() {
        return currentUpgrade;
    }

    // Apply the effects of the upgrade to the building this can be changed
    private void applyUpgradeEffects(Upgrade upgrade) {
        if (upgrade != null) {
            // Example: Reduce maintenance cost or increase capacity there should be more upgrades
            this.bill -= 50; // Example: Reduce monthly bill by 50 BilCoins
            this.info += "\nUpgrade Applied: " + upgrade.getName();
        }
    }
}
