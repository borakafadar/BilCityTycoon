package io.github.bilcitytycoon;

/**
 * Abstract base class representing a University in the game.
 * Can be extended by the Player and AI-controlled universities.
 * Tracks financials, satisfaction, dorm info, and reputation.
 */
public abstract class University implements Comparable<University> {

    /** The university's position in the leaderboard (1 = top). */
    public int leaderboardRanking;

    /** The name of the university. */
    public String name;

    /** The fixed base reputation (e.g., from facilities and events). */
    protected final int baseReputationPoints;

    /** The current reputation score, influenced by satisfaction. */
    protected int universityReputationPoint;

    /** Percentage (0–100) of student satisfaction. */
    protected int studentSatisfactionRate;

    /** Manages income, expenses, and balance. */
    protected MoneyHandler moneyHandler;

    /** Total number of students in the university. */
    protected int studentCount;

    /** Number of dorm slots currently filled. */
    protected int dormOccupancy;

    /** Maximum dorm capacity available. */
    protected int dormCapacity;

    /**
     * Constructs a University with basic attributes.
     * @param name University name
     * @param baseRep Base reputation score
     * @param satisfaction Initial satisfaction rate
     * @param studentCount Number of students enrolled
     * @param dormOccupancy Number of filled dorm slots
     * @param dormCapacity Total dorm capacity
     */
    public University(String name, int baseRep, int satisfaction, int studentCount, int dormOccupancy, int dormCapacity) {
        this.name = name;
        this.baseReputationPoints = baseRep;
        this.universityReputationPoint = baseRep;
        this.studentSatisfactionRate = satisfaction;
        this.moneyHandler = new MoneyHandler(2000);
        this.studentCount = studentCount;
        this.dormOccupancy = dormOccupancy;
        this.dormCapacity = dormCapacity;
    }

    public int getRanking() {
        return leaderboardRanking;
    }

    public int getBaseReputation() {
        return baseReputationPoints;
    }
    public String getName() {
        return this.name;
    }
    public int getUniversityReputationPoint() {
        return this.universityReputationPoint;
    }
    public int getStudentSatisfactionRate() {
        return this.studentSatisfactionRate;
    }

    /**
     * Sets the leaderboard rank.
     * @param rank The new rank (1 = highest)
     */
    public void setRankings(int rank) {
        this.leaderboardRanking = rank;
    }


    public MoneyHandler getMoneyHandler() {
        return this.moneyHandler;
    }

    /**
     * Adds income to the university's financials.
     * @param amount Amount of income
     */
    public void addIncome(int amount) {
        this.moneyHandler.updateIncome(amount);
    }

    /**
     * Adds an expense to the university's financials.
     * @param amount Amount of expense
     */
    public void addExpense(int amount) {
        moneyHandler.updateExpense(amount);
    }

    public int getNetIncome() {
        return moneyHandler.getNetIncome();
    }

    /**
     * Compares universities by descending reputation.
     */
    @Override
    public int compareTo(University u) {
        return Integer.compare(u.getUniversityReputationPoint(), this.getUniversityReputationPoint());
    }

    /**
     * Updates the university's total reputation points.
     * Used by the leaderboard system.
     * @param point New reputation point value
     */
    public void setUniversityReputationPoints(int point) {
        this.universityReputationPoint = point;
    }

    /**
     * Adjusts the student satisfaction rate with clamping (0–100).
     * @param adjustment Positive or negative change to satisfaction
     */
    public void adjustStudentSatisfactionRate(int adjustment) {
        this.studentSatisfactionRate += adjustment;
        if (this.studentSatisfactionRate > 100) this.studentSatisfactionRate = 100;
        if (this.studentSatisfactionRate < 0) this.studentSatisfactionRate = 0;
    }

    /**
     * Updates the university's reputation based on current satisfaction rate.
     * Uses a default multiplier (70).
     */
    public void updateReputationPoints() {
        this.universityReputationPoint = this.studentSatisfactionRate * 70;
    }

    /**
     * @return string summary of the university's rank, name, reputation, and satisfaction
     */
    @Override
    public String toString() {
        return String.format("#%d %s  (Rep=%d, Sat=%d%%)",
            leaderboardRanking, name, universityReputationPoint, studentSatisfactionRate);
    }
}
