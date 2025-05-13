package io.github.bilcitytycoon;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BilCityTycoonGame {
    public ArrayList<Building> allBuildings;
    public ArrayList<University> allUniversities;
    public Player player;
    public Map map;
    public Leaderboard leaderboard;
    public Store store;
    public BilCityTycoonGame(){
        this.player = new Player("Default",100,50);
        this.allBuildings = new ArrayList<Building>();
        this.map = new Map();
        this.allUniversities=new ArrayList<>();
        this.leaderboard = new Leaderboard(/*this,*/ this.player);
        this.store = new Store();
    }





    public void initializeGame(){
        //leaderboard initilaize
        allUniversities= leaderboard.getAllUniversities();
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
