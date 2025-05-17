package io.github.bilcitytycoon;

import java.util.ArrayList;

/**
 * Represents the human player in the game. Extends the {@link University} class and
 * includes money management, student data, dormitory data, and satisfaction tracking.
 */
public class Player extends University {

    /**
     * Player's current money balance in BilCoins.
     */
    public int coin;

    /**
     * Player's university name.
     */
    public String name;

    /**
     * Current reputation points of the university.
     */
    public int universityReputationPoint;

    /**
     * Student satisfaction percentage (0–100).
     */
    public int studentSatisfactionRate;

    /**
     * Raw satisfaction points (0–10000), used to compute satisfaction rate.
     */
    public int studentSatisfactionPoint;

    /**
     * List of all buildings owned by the player.
     */
    public ArrayList<Building> buildings;

    /**
     * Total number of students enrolled.
     */
    public int studentCount;

    /**
     * Number of students currently living in dormitories.
     */
    public int dormOccupancy;

    /**
     * Total dormitory capacity.
     */
    public int dormCapacity;

    /**
     * Constructs a player with full university data.
     *
     * @param name University name
     * @param universityReputationPoint Reputation score
     * @param studentSatisfactionRate Satisfaction percentage
     * @param studentCount Number of students
     * @param dormOccupancy Current dorm usage
     * @param dormCapacity Maximum dorm capacity
     */
    public Player(String name, int universityReputationPoint, int studentSatisfactionRate, int studentCount, int dormOccupancy, int dormCapacity) {
        super(name, universityReputationPoint, studentSatisfactionRate, studentCount, dormOccupancy, dormCapacity);
        this.name = name;
        this.universityReputationPoint = universityReputationPoint;
        this.studentSatisfactionRate = studentSatisfactionRate;
        this.studentSatisfactionPoint = this.studentSatisfactionRate * 100;
        this.studentCount = studentCount;
        this.dormOccupancy = dormOccupancy;
        this.buildings = new ArrayList<>();
    }

    /**
     * Default constructor used for fallback cases or deserialization.
     */
    public Player() {
        super("default", 0, 0, 0, 0, 0);
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int newCoin) {
        this.coin = newCoin;
    }

    public String getName() {
        return name;
    }

    public int getUniversityReputationPoint() {
        return universityReputationPoint;
    }

    void setUniversityReputationPoint(int newUniversityReputationPoint) {
        this.universityReputationPoint = newUniversityReputationPoint;
    }

    public int getStudentSatisfactionRate() {
        return studentSatisfactionRate;
    }

    public void setStudentSatisfactionRate(int newStudentSatisfactionRate) {
        this.studentSatisfactionRate = newStudentSatisfactionRate;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    /**
     * Adds a building to the player's list.
     *
     * @param building The building to add.
     */
    public void addBuilding(Building building) {
        //TODO
        this.buildings.add(building);
    }

    /**
     * Updates the satisfaction rate from raw point value.
     */
    public void calculateStudentSatisfactionRate() {
        this.studentSatisfactionRate = (int) (this.studentSatisfactionPoint / 10000.0 * 100);
    }

    /**
     * Increases student satisfaction points.
     *
     * @param amount Amount to increase.
     */
    public void addStudentSatisfactionPoint(int amount) {
        this.studentSatisfactionPoint += amount;
    }

    public void addStudentCount(int amount) {
        this.studentCount += amount;
    }

    public void addDormOccupancy(int amount) {
        this.dormOccupancy += amount;
    }

    public int getReputationPoints() {
        return this.universityReputationPoint;
    }

    public int getSatisfactionRate() {
        return this.studentSatisfactionRate;
    }

    public int getStudentCount() {
        return this.studentCount;
    }

    public int getDormOccupancy() {
        return this.dormOccupancy;
    }

    public int getDormCapacity() {
        return this.dormCapacity;
    }

    /**
     * Generates a filename-safe save identifier based on the player's university name.
     *
     * @return A lowercase, hyphen-separated string (e.g., "bilkent-university-")
     */
    public String getSaveFileName() {
        String[] words = getName().split(" ");
        StringBuilder saveFileName = new StringBuilder();
        for (String str : words) {
            saveFileName.append(str.toLowerCase()).append("-");
        }
        return saveFileName.toString();
    }

    public void setName(String playerName) {
        this.name = playerName;
    }

    /**
     * Gradually decreases student satisfaction points over time.
     *
     * @param deltaTime Time passed since the last update in seconds.
     */
    public void decreaseStudentSatisfactionPointPerSecond(float deltaTime) {
        int pointsToDecrease = (int) (deltaTime * 100f);
        if (deltaTime > 0 && pointsToDecrease == 0) {
            pointsToDecrease = 1;
        }

        this.studentSatisfactionPoint -= pointsToDecrease;

        if (this.studentSatisfactionPoint < 0) {
            this.studentSatisfactionPoint = 0;
        }

        calculateStudentSatisfactionRate();
    }

    /**
     * Adds a building directly to the player's structure list.
     *
     * @param building The building to construct.
     */
    public void constructBuilding(Building building) {
        buildings.add(building);
    }

    /**
     * Returns whether satisfaction has dropped to zero or below.
     *
     * @return true if bankrupt of satisfaction, false otherwise.
     */
    public boolean isBankruptOfSatisfaction() {
        return this.studentSatisfactionPoint <= 0;
    }

    /**
     * Safely decreases student satisfaction points without going negative.
     *
     * @param amount Amount to decrease.
     */
    public void safeDecreaseStudentSatisfactionPoint(int amount) {
        this.studentSatisfactionPoint -= amount;
        if (this.studentSatisfactionPoint < 0) {
            this.studentSatisfactionPoint = 0;
        }
        calculateStudentSatisfactionRate();
    }
}
