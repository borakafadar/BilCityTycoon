package io.github.bilcitytycoon;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class OtherBuilding extends Building{
    private double income;

    public OtherBuilding(String name, double cost, double income) {
        super(name, cost);
        this.income = income;
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
        return 0;
        //TODO temp
    }



}

