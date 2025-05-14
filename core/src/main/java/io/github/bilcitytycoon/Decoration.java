package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Decoration{
    public String name;
    public String info;
    public int cost;
    public transient Image image;
    public int buildTime;
    public String imagePath;

    public Decoration(String name, int cost,String imagePath,String info, int buildTime){
        this.name = name;
        this.cost = cost;
        this.imagePath = imagePath;
        this.image = new Image(new Texture(Gdx.files.internal(imagePath)));
        this.info = info;
        this.buildTime = buildTime;
    }
    public Decoration(){
        this.name = "yasar";
    }
    public String getInfo() {
        return this.info;
    }
    public Image getImage(){
        return this.image;
    }
    public int getCost(){
        return this.cost;
    }
    public String getName(){
        return this.name;
    }
    public int getBuildTime(){return this.buildTime;}
    public String getImagePath(){
        return this.imagePath;
    }
    public void setImage(Image image){
        this.image = image;
    }
}
