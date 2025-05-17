package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json.Serializable;

public class Decoration implements Serializable {
    private float x;
    private float y;
    public String name;
    public String info;
    public int cost;
    public transient Image image;
    public int buildTime;
    public String imagePath;

    @Override
    public void write(Json json) {
        json.writeValue("name", name);
        json.writeValue("info", info);
        json.writeValue("cost", cost);
        json.writeValue("imagePath", imagePath);
        json.writeValue("buildTime", buildTime);
        json.writeValue("x", x);
        json.writeValue("y", y);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        name = jsonData.getString("name");
        info = jsonData.getString("info");
        cost = jsonData.getInt("cost");
        imagePath = jsonData.getString("imagePath");
        buildTime = jsonData.getInt("buildTime");
        x = jsonData.getFloat("x");
        y = jsonData.getFloat("y");
        image = new Image(new Texture(Gdx.files.internal(imagePath)));
        image.setPosition(x, y);
    }



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
    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }


}
