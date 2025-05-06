package io.github.bilcitytycoon;

import java.util.ArrayList;

public class BilCityTycoonGame {
    private ArrayList<Building> allBuildings;
    private ArrayList<Player> allUniversities;
    private Player player;
    private Map map;
    private Leaderboard leaderboard;
    public BilCityTycoonGame(){
        this.allBuildings = new ArrayList<Building>();
        this.map = new Map();
        this.allUniversities=new ArrayList<>();
        this.leaderboard = new Leaderboard(this, this.player);
    }


    public void initializeGame(){
        //leaderboard initilaize
        //TODO
    }

    public Leaderboard getLeaderboard(){
        return this.leaderboard;
    }

    public Player getPlayer(){return this.player;}
}
