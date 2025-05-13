package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class OtherBuilding extends Building{
    public int income;
    public int buildTime;
    public transient Image image;
    public String info;
    public String imagePath;
    private int dormitoryCapacity;

    public OtherBuilding(String name, int cost, int income, int bill, int buildTime, String imagePath, String info,BilCityTycoonGame game) {
        super(name, cost, bill, 100, 100);
        this.income = income;
        this.buildTime = buildTime;
        this.info = info;
        this.dormitoryCapacity = 0; // Default capacity
        this.imagePath = imagePath;

        // Load the image for the building, with validation
        if (imagePath == null || imagePath.isEmpty()) {
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }

        if (Gdx.files.internal(imagePath).exists()) {
            this.image = new Image(new Texture(Gdx.files.internal(this.imagePath)));
        } else {
            throw new IllegalArgumentException("Image file not found: " + imagePath);
        }
    }
    public OtherBuilding(){
        super("other building",0,0, 200, 200);
    }
    // Getter methods for the building's properties
    public double getIncome() {
        return this.income;
    }

    public String getInfo(){
        return this.info;
    }
    public Image getImage(){

        return image;
    }
    public int getBuildTime(){
        return this.buildTime;
    }

    // Override toString for a detailed representation
    @Override
    public String toString() {
        return "OtherBuilding{name='" + name + "', cost=" + buildCost + ", bill=" + bill +
                ", income=" + income + ", buildTime=" + buildTime + "}";
    }

    public void setDormitoryCapacity(int capacity) {
        this.dormitoryCapacity = capacity;
    }

    public int getDormitoryCapacity() {
        return this.dormitoryCapacity;
    }


}

