package io.github.bilcitytycoon;

import java.awt.font.TextHitInfo;
import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private int coin;
    private String name;
    private int universityReputationPoint;
    private int studentSatisfactionRate;
    private ArrayList<Building> buildings;
    private Leaderboard leaderboardRanking;


    public Player(String name, int universityReputationPoint, int studentSatisfactionRate, ArrayList<Building> buildings, Leaderboard leaderboardRanking)
    {
        this.name = name;
        this.universityReputationPoint = universityReputationPoint;
        this.studentSatisfactionRate = studentSatisfactionRate;
        this.buildings = buildings;
        this.leaderboardRanking = leaderboardRanking;
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

    @Override
    public int compareTo(Player p) {
        //TODO: comparable method to use for leaderboard ranking
    }

}
