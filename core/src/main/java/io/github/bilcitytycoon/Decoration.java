package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Decoration{
    private String name;
    private String info;
    private double cost;
    private Image image;

    public Decoration(String name, double cost,String imagePath,String info){
        this.name = name;
        this.cost = cost;
        this.image = new Image(new Texture(Gdx.files.internal(imagePath)));
        this.info = info;
    }
    public String getInfo() {
        return this.info;
    }
    public Image getImage(){
        return this.image;
    }
    public double getCost(){
        return this.cost;
    }
    public String getName(){
        return this.name;
    }
}
