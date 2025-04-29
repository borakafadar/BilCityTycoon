package io.github.bilcitytycoon;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private ArrayList<Building> allBuildings;
    private ArrayList<Player> allUniversities;
    private Player player;
    private Map map;
    private Leaderboard leaderboard;
    public Game(){
        this.allBuildings = new ArrayList<Building>();
        this.map = new Map();
        this.allUniversities=new ArrayList<>();
        this.leaderboard = new Leaderboard();
    }


    public void initializeGame(){
        //leaderboard initilaize
        //TODO
    }
}
