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
    public ArrayList<Decoration> placedDecorations;
    public Leaderboard leaderboard;
    public Store store;
    public String endingTitle;
    public String endingInfo;
    public String leftButtonText;
    public String rightButtonText;
    public boolean debugShowAllEndings = false;
    public Time time;

    public BilCityTycoonGame(){
        this.placedDecorations = new ArrayList<>();
        this.time = new Time();
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
    }

    public void checkEnding(Main main){
        boolean ultimateEnding = this.getPlayer().getRanking() == 1 && this.player.getStudentSatisfactionRate() >= 90;
        boolean trueEnding = this.getPlayer().getRanking() <= 5;
        boolean bankruptEnding = this.getPlayer().getCoin() <= 0;
        boolean lawsuitEnding = this.getPlayer().getStudentSatisfactionRate() <= 15;

        if(ultimateEnding || trueEnding || bankruptEnding || lawsuitEnding) {
            if (ultimateEnding) {
                presentUltimateEnding();
                EndingScreen end = new EndingScreen(this, main, endingTitle,endingInfo, leftButtonText, rightButtonText, main.getScreen());
                main.setScreen(end);
            } else if (trueEnding) {
                presentTrueEnding();
                EndingScreen end = new EndingScreen(this, main, endingTitle,endingInfo, leftButtonText, rightButtonText, main.getScreen());
                main.setScreen(end);
            } else if (bankruptEnding) {
                presentBankruptEnding();
                EndingScreen end = new EndingScreen(this, main, endingTitle,endingInfo, leftButtonText, main.getScreen(), rightButtonText);
                main.setScreen(end);
            } else if (lawsuitEnding) {
                presentLawsuitEnding();
                EndingScreen end = new EndingScreen(this, main, endingTitle,endingInfo, leftButtonText, main.getScreen(), rightButtonText);
                main.setScreen(end);
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
        throw new UnsupportedOperationException("Unimplemented method 'getSkin'");
    }


    public Stage getStage() {
        throw new UnsupportedOperationException("Unimplemenzted method 'getStage'");
    }
    public Player getPlayer() {
        return this.player;
    }
    public Map getMap() {
        return this.map;
    }
    public ArrayList<Building> getBuildings() {
        return allBuildings;
    }
    public String getEndingTitle() {
        return endingTitle;
    }

    public String getEndingInfo() {
        return endingInfo;
    }

    public String getLeftButtonText() {
        return leftButtonText;
    }

    public String getRightButtonText() {
        return rightButtonText;
    }
    public String getCurrentEndingStatus() {
        StringBuilder status = new StringBuilder();

        if (player.getCoin() <= 0) {
            status.append("💰 Bankrupt Ending Active\n");
        }
        if (player.getStudentSatisfactionRate() <= 15) {
            status.append("⚖ Lawsuit Ending Active\n");
        }
        if (player.getRanking() == 1 && player.getStudentSatisfactionRate() >= 90) {
            status.append("🏆 Ultimate Ending Available\n");
        }
        if (player.getRanking() <= 5) {
            status.append("🥈 True Ending Available\n");
        }

        if (status.length() == 0) {
            status.append("🚀 No Ending Condition Met Yet");
        }

        return status.toString();
    }
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

}
