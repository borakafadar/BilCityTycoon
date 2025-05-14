package io.github.bilcitytycoon;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.bilcitytycoon.Screens.EndingScreen;

public class BilCityTycoonGame {
    public ArrayList<Building> allBuildings;
    public ArrayList<University> allUniversities;
    public Player player;
    public Map map;
    public Leaderboard leaderboard;
    public Store store;
    private String endingTitle;
    private String endingInfo;
    private String leftButtonText;
    private String rightButtonText;

    public BilCityTycoonGame(){
        this.player = new Player("Default",100,50,10,10,10);
        this.allBuildings = new ArrayList<Building>();
        this.map = new Map("North Campus", 20, 15);
        this.allUniversities=new ArrayList<>();
        this.leaderboard = new Leaderboard(/*this,*/ this.player);
        this.store = new Store();
    }

    public void initializeGame(){
        //leaderboard initilaize
        allUniversities= leaderboard.getAllUniversities();
        //TODO
    }

    public void checkEnding(Main main){
        boolean ultimateEnding = this.player.getRanking() == 1 && this.player.getStudentSatisfactionRate() >= 90;
        boolean trueEnding = this.player.getRanking() <= 5;
        boolean bankruptEnding = this.player.getCoin() <= 0;
        boolean lawsuitEnding = this.player.getStudentSatisfactionRate() <= 15;

        if(ultimateEnding || trueEnding || bankruptEnding || lawsuitEnding) {
            if (ultimateEnding) {
                presentUltimateEnding();
                EndingScreen end = new EndingScreen(this, main, endingTitle,endingInfo, leftButtonText, rightButtonText,main.getScreen());
            } else if (trueEnding) {
                presentTrueEnding();
                EndingScreen end = new EndingScreen(this, main, endingTitle,endingInfo, leftButtonText, rightButtonText,main.getScreen());
            } else if (bankruptEnding) {
                presentBankruptEnding();
                EndingScreen end = new EndingScreen(this, main, endingTitle,endingInfo, leftButtonText,main.getScreen(), rightButtonText);
            } else if (lawsuitEnding) {
                presentLawsuitEnding();
                EndingScreen end = new EndingScreen(this, main, endingTitle,endingInfo, leftButtonText,main.getScreen(), rightButtonText);
            }
        }
    }

    public void presentUltimateEnding(){
        endingTitle = "Ultimate Ending";
        endingInfo = "You have achieved the Ultimate Ending by ranking first in the University Leaderboard with a student satisfaction rate of 90%! Do you want to continue playing the game?";
        leftButtonText = "Yes";
        rightButtonText = "No";
    }

    public void presentTrueEnding(){
        endingTitle = "True Ending";
        endingInfo = "You have achieved the True Ending by ranking top 5 in the University Leaderboard with a student satisfaction rate of 80%! Do you want to continue playing the game?";
        leftButtonText = "Yes";
        rightButtonText = "No";
    }
    public void presentBankruptEnding(){
        endingTitle = "Bankrupt Ending";
        endingInfo = "Uh-oh! It looks like your money has been run out! You lost the game!";
        leftButtonText = "Load Game";
        rightButtonText = ":(";
    }
    public void presentLawsuitEnding(){
        endingTitle = "Lawsuit Ending";
        endingInfo = "Uh-oh! Due to the recent events, you have been sued by many people! You have been terminated. You lost the game!";
        leftButtonText = "Load Game";
        rightButtonText = ":(";
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
    public Map getMap() {
        return this.map;
    }
}
