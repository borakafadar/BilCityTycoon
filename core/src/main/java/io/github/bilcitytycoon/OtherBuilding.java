package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class OtherBuilding extends Building implements Json.Serializable{
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


    public String getInfo(){
        return this.info;
    }
    @Override
    public Image getImage() {
        if (image == null && imagePath != null && Gdx.files.internal(imagePath).exists()) {
            image = new Image(new Texture(Gdx.files.internal(imagePath)));
        }
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
    @Override
    public void write(Json json) {
        json.writeValue("type", this.getClass().getName());
        json.writeValue("name", name);
        json.writeValue("buildCost", buildCost);
        json.writeValue("bill", bill);
        json.writeValue("studentSatisfactionPoint", studentSatisfactionPoint);
        json.writeValue("universityReputationPoint", universityReputationPoint);
        json.writeValue("income", income);
        json.writeValue("imagePath", imagePath);
        json.writeValue("info", info);
        json.writeValue("buildTime", buildTime);
        json.writeValue("dormitoryCapacity", dormitoryCapacity);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        this.name = jsonData.getString("name");
        this.buildCost = jsonData.getInt("buildCost");
        this.bill = jsonData.getInt("bill");
        this.studentSatisfactionPoint = jsonData.getInt("studentSatisfactionPoint");
        this.universityReputationPoint = jsonData.getInt("universityReputationPoint");
        this.income = jsonData.getInt("income");
        this.imagePath = jsonData.getString("imagePath");
        this.info = jsonData.getString("info");
        this.buildTime = jsonData.getInt("buildTime");
        this.dormitoryCapacity = jsonData.getInt("dormitoryCapacity", 0);

        if (imagePath != null && Gdx.files.internal(imagePath).exists()) {
            this.image = new Image(new Texture(Gdx.files.internal(imagePath)));
        }
    }


}

