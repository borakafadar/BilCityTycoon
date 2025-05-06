package io.github.bilcitytycoon;

import java.awt.font.TextHitInfo;
import java.util.ArrayList;

public class Player extends University {
    private double coin;
    private String name;
    private int universityReputationPoint;
    private int studentSatisfactionRate;
    private ArrayList<Building> buildings;
    private Leaderboard leaderboard;

    public Player(String name, int universityReputationPoint, int studentSatisfactionRate, Leaderboard leaderboard)
    {
        this.name = name;
        this.universityReputationPoint = universityReputationPoint;
        this.studentSatisfactionRate = studentSatisfactionRate;
        this.buildings = new ArrayList<>();
        this.leaderboard = leaderboard;
    }

    public double getCoin() {
        return coin;
    }
    public void setCoin(double newCoin) {
        this.coin = newCoin;
    }
    public String getName() {
        return name;
    }
    public int getUniversityReputationPoint()
    {
        return universityReputationPoint;
    }
    public void setUniversityReputationPoint(int newUniversityReputationPoint){
        this.universityReputationPoint = newUniversityReputationPoint;
    }
    public int getStudentSatisfactionRate(){
        return studentSatisfactionRate;
    }
    public void setStudentSatisfactionRate(int newStudentSatisfactionRate){
        this.studentSatisfactionRate = newStudentSatisfactionRate;
    }
    public ArrayList<Building> getBuildings(){
        return buildings;
    }

    public void addBuilding(Building building){
        //TODO
        this.buildings.add(building);
    }


     public int getReputationPoints() {
         return this.universityReputationPoint;
     }

    public int getSatisfactionRate() {
         return this.studentSatisfactionRate;
     }
}
