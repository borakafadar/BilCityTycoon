package io.github.bilcitytycoon;

import java.awt.font.TextHitInfo;
import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private int coin;
    private String name;
    private int universityReputationPoint;
    private int studentSatisfactionRate;
    private ArrayList<Building> buildings;
    private int leaderboardRanking;
    private Leaderboard leaderboard;

    public Player(String name, int universityReputationPoint, int studentSatisfactionRate, Leaderboard leaderboard)
    {
        this.name = name;
        this.universityReputationPoint = universityReputationPoint;
        this.studentSatisfactionRate = studentSatisfactionRate;
        this.buildings = new ArrayList<>();
        this.leaderboard = leaderboard;
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

    @Override
    public int compareTo(Player p) {
        //TODO: comparable method to use for leaderboard ranking
        return 1;
    }

}
