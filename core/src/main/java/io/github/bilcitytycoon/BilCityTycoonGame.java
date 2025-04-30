package io.github.bilcitytycoon;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

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
        this.leaderboard = new Leaderboard();
    }


    public void initializeGame(){
        //leaderboard initilaize
        //TODO
    }


    // public Skin getSkin() {
    //     // TODO 
    // }


    // public Stage getStage() {
    //     // TODO 
    // }
}
