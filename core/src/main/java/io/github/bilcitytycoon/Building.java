
package io.github.bilcitytycoon;

/*
 * Abstract class representing a building in the game.
 * It contains common properties and methods for all buildings.
 * Specific building types (e.g., Faculty, Rectorate) will extend this class.
 */
public abstract class Building {

    private static final String UPGRADE_TYPE1 ="Ventilation";
    private static final String UPGRADE_TYPE2 ="Energy Efficiency";
    private static final String UPGRADE_TYPE3 ="Capacity";
    protected String name;
    protected String info;
    protected int buildCost;
    protected int bill;
    protected int constructionTime;
    protected Upgrade[] upgrades; // Array to hold multiple upgrades
    protected int currentUpgradeLevel;//tracks the current upgrade
    protected int studentSatisfactionPoint;
    protected int universityReputationPoint;


    // Constructor for the Building class
    // Initializes the building's name, cost, and bill.
    public Building(String name, int cost, int bill,int studentSatisfactionPoint,int universityReputationPoint) {
        this.upgrades = new Upgrade[3]; // Initialize the upgrades array with a size of 3
        this.info = "No additional information";
        this.constructionTime = 0;
        this.name = name;
        this.buildCost = cost;
        this.bill = bill;
        this.currentUpgradeLevel = 0; // Tracks the current level of upgrade

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

    public void addUpgrade(Upgrade upgrade, int level){
        if (level < 1 || level > upgrades.length) {
            throw new IllegalArgumentException("Invalid upgrade level: " + level);
        }
        upgrades[level - 1] = upgrade;
    }

    public Upgrade[] getUpgrades() {
        return upgrades;
    }

    public int getCurrentUpgradeLevel() {
        return currentUpgradeLevel;
    }

    // Apply the next upgrade level
    public void applyNextUpgrade(String info,BilCityTycoonGame game) {
        if (currentUpgradeLevel < upgrades.length && upgrades[currentUpgradeLevel] != null) {
            Upgrade upgrade = upgrades[currentUpgradeLevel];
            upgrade.applyUpgrade(); // Mark the upgrade as applied
            applyUpgradeEffects(upgrade, info, game); // Apply the effects of the upgrade
            currentUpgradeLevel++; // Move to the next upgrade level
        } else {
            System.out.println("No more upgrades available or upgrade not defined for this level.");
        }
    }

    // Apply the effects of the upgrade to the building
    private void applyUpgradeEffects(Upgrade upgrade, String info,BilCityTycoonGame game) {
        Player pl = game.getPlayer();
        if (upgrade != null) {
            // Example: Reduce maintenance cost or increase capacity
            if(UPGRADE_TYPE1.equals(upgrade.getType())) {
                this.bill -= 20; // Example: Reduce monthly bill by 20 BilCoins
            } else if (UPGRADE_TYPE2.equals(upgrade.getType())) {
                this.bill -= 30; // Example: Reduce monthly bill by 30 BilCoins
            } else if (UPGRADE_TYPE3.equals(upgrade.getType())) {
                if(upgrade.getBuilding() instanceof OtherBuilding){
                    //pl.addStudenSatisfactionPoint(5); // Example: Increase student satisfaction by 5 points
                    OtherBuilding otherBuilding = (OtherBuilding) upgrade.getBuilding();
                    otherBuilding.setDormitoryCapacity(otherBuilding.getDormitoryCapacity() + 10); // Example: Increase capacity by 10
                }
                else if(upgrade.getBuilding() instanceof Faculty){
                    Faculty faculty = (Faculty) upgrade.getBuilding();
                    faculty.setConstructionTime(faculty.getConstructionTime() + 5); // Example: Increase construction time by 5 days
                }

            }

            this.bill -= 50; // Example: Reduce monthly bill by 50 BilCoins
            this.info += "\n " + info + upgrade.getName();
        }
    }

    public int getStudentSatisfactionPoint() {
        return studentSatisfactionPoint;
    }

}
