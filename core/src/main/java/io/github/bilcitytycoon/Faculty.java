package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Faculty extends Building {

    private double income;
    private Image image;
    private String info;
    private int buildTime;

    public Faculty(String name, int cost, int bill, int income,String imagePath,String info, int buildTime) {
        super(name, cost, bill);
        this.income = income;
        this.image = new Image(new Texture(Gdx.files.internal(imagePath)));
        this.info = info;
        this.buildTime = buildTime;
    }


    public double getBuildCost() {
        return this.buildCost;
    }

    public double getBill() {
        return this.bill;
    }

    public double getIncome() {
        return this.income;
    }

    public Image getImage(){
        return this.image;
    }
    public int getBuildTime(){
        return this.buildTime;
    }

    @Override
    public String getInfo() {
        //TODO
        return this.info;
    }
}
