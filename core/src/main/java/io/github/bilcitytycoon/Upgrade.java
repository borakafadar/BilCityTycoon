package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Upgrade {
    private String name; //Name of the upgrade
    private int upgradeCost; //Cost of the upgrade
    private int constructionTime; //Time required to complete the upgrade
    private Building building; // The building associated with the upgrade
    private String info; //Additional information about the upgrade
    private boolean isMade; //A flag to check whether the upgrade is made
    private Image image; //Visual representation of the upgrade

    //public String

    //Constructor
    public Upgrade(String name, int cost, int constructionTime, String imagePath, String info, Building building) {
        this.name = name;
        this.upgradeCost = cost;
        this.constructionTime = constructionTime;
        this.info = info;
        this.building = building;
        this.isMade = false; // Initially set to false

        // Load the image for the upgrade, with validation
        if (imagePath != null && !imagePath.isEmpty() && Gdx.files.internal(imagePath).exists()) {
            this.image = new Image(new Texture(Gdx.files.internal(imagePath)));
        } else {
            throw new IllegalArgumentException("Invalid image path: " + imagePath);
        }
    }
    //Method to apply the upgrade
    public void applyUpgrade() {
        // Apply the upgrade to the building
        //May be a building capaciy increase or maintainance cost decrease
        if (!isMade) {
            
            building.setUpgrade(this);
            isMade = true; 
        } else {
            System.out.println("Upgrade already applied.");
        }
    }

    //Method to update the player's budget
    public int updateBudget(int currentBudget){
         if (currentBudget >= upgradeCost) {
            currentBudget -= upgradeCost;
            System.out.println("Upgrade cost deducted: " + upgradeCost + " BilCoins");
        } else {
            System.out.println("Insufficient funds for upgrade: " + name);
        }
        return currentBudget;
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

    @Override
    public String toString(){return "";}
}
