package io.github.bilcitytycoon;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private int coin;
    private String name;
    private int universityReputationPoint;
    private int studentSatisfactionRate;
    private ArrayList<Building> buildings;
    private int leaderboardRanking;



    public int getCoin() {
        return coin;
    }
    public void setCoin(int newCoin) {
        this.coin = newCoin;
    }
    public String getName() {
        return name;
    }


    @Override
    public int compareTo(Player o) {
        //TODO: comparable method to use for leaderboard ranking
    }

}
