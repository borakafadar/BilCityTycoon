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
        this.player = new Player("Default", 100, 80, leaderboard);
        this.allBuildings = new ArrayList<Building>();
        this.map = new Map();
        this.allUniversities=new ArrayList<>();
        this.leaderboard = new Leaderboard(/*this,*/ this.player);
    }


    public void initializeGame(){
        //leaderboard initilaize
        //TODO
    }

    public Leaderboard getLeaderboard(){
        return this.leaderboard;
    }


    public Skin getSkin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSkin'");
    }


    public Stage getStage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemenzted method 'getStage'");
    }
    public Player getPlayer() {
        return this.player;
    }
}
