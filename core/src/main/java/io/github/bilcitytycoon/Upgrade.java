package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Upgrade {
    private String name;
    private int upgradeCost;
    private int constructionTime;
    private Building building;
    private String info;
    private boolean isMade;
    private Image image;

    //public String

    public void updateBudget(){
        //TODO
    }

    public Image getImage(){
        return this.image;
    }
    public String getInfo(){
        return this.info;
    }
    public String getName(){
        return this.name;
    }
    public int getUpgradeCost(){
        return this.upgradeCost;
    }
    public int getBuildTime(){
        return this.constructionTime;
    }
    public Building getBuilding(){
        return this.building;
    }

}
