package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class OtherBuilding extends Building{
    private int income;
    private int buildTime;
    private Image image;
    private String info;

    public OtherBuilding(String name, int cost, int income, int bill, int buildTime, String imagePath, String info) {
        super(name, cost, bill);
        this.income = income;
        this.buildTime = buildTime;
        this.image = new Image(new Texture(Gdx.files.internal(imagePath)));
        this.info = info;
    }

    public double getIncome() {
        return this.income;
    }

    public String getInfo(){
        //TODO
        return null; //temp
    }
    public Image getImage(){
        return null;
        //TODO temp
    }
    public int getBuildTime(){
        return this.buildTime;
    }



}

