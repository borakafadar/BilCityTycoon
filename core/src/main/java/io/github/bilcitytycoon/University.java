package io.github.bilcitytycoon;

import java.security.PublicKey;

public abstract class University implements Comparable<University>{
    public int leaderboardRanking;
    public String name;
    //this is the current reputation points
    protected final int baseReputationPoints; //from facilities, events
    protected int universityReputationPoint;
    protected int studentSatisfactionRate;
    protected MoneyHandler moneyHandler;
    protected int studentCount;
    protected int dormOccupancy;
    protected int dormCapacity;


    //The constructor
    public University(String name, int baseRep,int satisfaction,int studentCount,int dormOccupancy,int dormCapacity) {
        this.name = name;
        this.baseReputationPoints = baseRep;
        this.universityReputationPoint = baseRep;
        this.studentSatisfactionRate = satisfaction;
        this.moneyHandler = new MoneyHandler(2000);
        this.studentCount = studentCount;
        this.dormOccupancy = dormOccupancy;
        this.dormCapacity = dormCapacity;

    }
    //Getter methods
    public int getRanking(){
        return leaderboardRanking;
    }

    public int getBaseReputation() {
        return baseReputationPoints;
    }
    public String getName(){
        return this.name;
    }
    public int getUniversityReputationPoint(){
        return this.universityReputationPoint;
    }
    public int getStudentSatisfactionRate(){
        return this.studentSatisfactionRate;
    }

    public void setRankings(int rank){
        this.leaderboardRanking = rank;
    }

    public MoneyHandler getMoneyHandler(){
        return this.moneyHandler;
    }

    //Add income to the university
    public void addIncome(int amount){
        this.moneyHandler.updateIncome(amount);
    }
    // Add an expense to the university
    public void addExpense(int amount) {
        moneyHandler.updateExpense(amount);
    }

    // Get the net income of the university
    public int getNetIncome() {
        return moneyHandler.getNetIncome();
    }

    @Override
    public int compareTo(University u) {
        return Integer.compare(u.universityReputationPoint, this.universityReputationPoint);
    }

    //used by leaderboard.updateRanking()
    public void setUniversityReputationPoints(int point) {
       this.universityReputationPoint = point;
    }

    // Adjust student satisfaction rate
    public void adjustStudentSatisfactionRate(int adjustment) {
        this.studentSatisfactionRate += adjustment;
        if (this.studentSatisfactionRate > 100) this.studentSatisfactionRate = 100;
        if (this.studentSatisfactionRate < 0) this.studentSatisfactionRate = 0;
    }

    // Update reputation points based on satisfaction rate
    public void updateReputationPoints() {
        this.universityReputationPoint = this.studentSatisfactionRate * 70; // Example multiplier
    }

    //This might be changed
    @Override
    public String toString(){
        return String.format("#%d %s  (Rep=%d, Sat=%d%%)",
        leaderboardRanking, name, universityReputationPoint, studentSatisfactionRate);
    }
}
