package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Represents an upgrade that can be applied to a building in the game.
 * Each upgrade affects income, expenses, satisfaction, and reputation.
 */
public class Upgrade {

    /** Upgrade type constant: Ventilation */
    private static final String UPGRADE_TYPE1 = "Ventilation";

    /** Upgrade type constant: Energy Efficiency */
    private static final String UPGRADE_TYPE2 = "Energy Efficiency";

    /** Upgrade type constant: Capacity */
    private static final String UPGRADE_TYPE3 = "Capacity";

    /** Name of the upgrade */
    private String name;

    /** Cost of the upgrade */
    private int upgradeCost;

    /** Time required to complete the upgrade */
    private int constructionTime;

    /** The building this upgrade is associated with */
    private Building building;

    /** Description or additional information about the upgrade */
    private String info;

    /** Whether this upgrade has been made or not */
    private boolean isMade;

    /** Image representing the upgrade (transient, not serialized) */
    private transient Image image;

    /** Type/category of upgrade */
    private String upgradeType;

    /** File path to the upgrade's image asset */
    private String imagePath;

    /**
     * Constructor for Upgrade
     *
     * @param name Name of the upgrade
     * @param cost Cost of the upgrade
     * @param constructionTime Time required to build
     * @param imagePath File path to image
     * @param info Description text
     * @param building Building to which this upgrade applies
     * @param upgradeType Category/type of upgrade
     */
    public Upgrade(String name, int cost, int constructionTime, String imagePath, String info, Building building, String upgradeType) {
        this.name = name;
        this.upgradeCost = cost;
        this.constructionTime = constructionTime;
        this.info = info;
        this.building = building;
        this.isMade = false;
        this.upgradeType = upgradeType;
        this.imagePath = imagePath;

        // Load the image for the upgrade, with validation
        if (imagePath != null && !imagePath.isEmpty() && Gdx.files.internal(imagePath).exists()) {
            this.image = new Image(new Texture(Gdx.files.internal(imagePath)));
        } else {
            throw new IllegalArgumentException("Invalid image path: " + imagePath);
        }
    }

    /**
     * Updates income and expense according to the upgrade type.
     */
    public void updateBudget(MoneyHandler moneyHandler) {
        switch (upgradeType) {
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

    /**
     * Applies the upgrade if not already applied.
     * Increases income, reputation, and satisfaction.
     */
    public void applyUpgrade(MoneyHandler moneyHandler, Player player) {
        if (isMade) {
            return;
        }
        this.isMade = true;

        updateBudget(moneyHandler);
        updatePlayerReputation(player);
        updateStudentSatisfactionPoint(player);
    }

    /**
     * Increases the player's reputation.
     */
    private void updatePlayerReputation(Player player) {
        player.setUniversityReputationPoint(player.getUniversityReputationPoint() + 100);
    }

    /**
     * Increases the player's satisfaction points.
     */
    private void updateStudentSatisfactionPoint(Player player) {
        player.addStudentSatisfactionPoint(50);
    }

    public Image getImage() {
        return this.image;
    }

    public String getInfo() {
        return this.info;
    }

    public String getName() {
        return this.name;
    }

    public int getUpgradeCost() {
        return this.upgradeCost;
    }

    public int getBuildTime() {
        return this.constructionTime;
    }

    public Building getBuilding() {
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
    public String toString() {
        return "";
    }
}
