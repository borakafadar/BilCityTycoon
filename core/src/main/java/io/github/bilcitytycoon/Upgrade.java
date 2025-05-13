package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Upgrade {
    private static final String UPGRADE_TYPE1 ="Ventilation";
    private static final String UPGRADE_TYPE2 ="Energy Efficiency";
    private static final String UPGRADE_TYPE3 ="Capacity";
    private String name; //Name of the upgrade
    private int upgradeCost; //Cost of the upgrade
    private int constructionTime; //Time required to complete the upgrade
    private Building building; // The building associated with the upgrade
    private String info; //Additional information about the upgrade
    private boolean isMade; //A flag to check whether the upgrade is made
    private transient Image image; //Visual representation of the upgrade
    private String upgradeType; //Type of the upgrade (e.g., Ventilation, Energy Efficiency, Capacity)
    private String imagePath;

    //Constructor
    public Upgrade(String name, int cost, int constructionTime, String imagePath, String info, Building building, String upgradeType) {
        this.name = name;
        this.upgradeCost = cost;
        this.constructionTime = constructionTime;
        this.info = info;
        this.building = building;
        this.isMade = false; // Initially set to false
        this.upgradeType = upgradeType;
        this.imagePath = imagePath;

        // Load the image for the upgrade, with validation
        if (imagePath != null && !imagePath.isEmpty() && Gdx.files.internal(imagePath).exists()) {
            this.image = new Image(new Texture(Gdx.files.internal(imagePath)));
        } else {
            throw new IllegalArgumentException("Invalid image path: " + imagePath);
        }
    }

    public void updateBudget(MoneyHandler moneyHandler){
        switch(upgradeType){
            case UPGRADE_TYPE1:
                moneyHandler.updateIncome(120);
                moneyHandler.updateExpense(80);
                break;
            case UPGRADE_TYPE2:
                moneyHandler.updateIncome(150);
                moneyHandler.updateExpense(60);
                break;
            case UPGRADE_TYPE3:
                moneyHandler.updateIncome(250);
                moneyHandler.updateExpense(100);
                break;
            default:
        }
    }

    //Method to apply the upgrade
    public void applyUpgrade(MoneyHandler moneyHandler, Player player) {
        if(isMade){
            return;
        }
        this.isMade = true;

        updateBudget(moneyHandler);
        updatePlayerReputation(player);
        updateStudentSatisfactionPoint(player);
        // Apply the upgrade to the building
        //May be a building capaciy increase or maintainance cost decrease
    }

    private void updatePlayerReputation(Player player) {
        player.setUniversityReputationPoint(player.getUniversityReputationPoint() + 100);
    }

    private void updateStudentSatisfactionPoint(Player player) {
        player.addStudentSatisfactionPoint(50);
    }



    //Getters
    public Image getImage(){
        return this.image;
    }
    public String getInfo(){
        return this.info;
    }
    public String getName(){
        return this.name;
    }
    public int getUpgradeCost(){
        return this.upgradeCost;
    }
    public int getBuildTime(){
        return this.constructionTime;
    }
    public Building getBuilding(){
        return this.building;
    }

    public boolean isMade() {
        return isMade;
    }

    public int getConstructionTime() {
        return constructionTime;
    }

    public String getType() {
        return upgradeType;
    }

    @Override
    public String toString(){return "";}
}
