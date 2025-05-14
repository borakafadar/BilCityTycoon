
package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/*
 * Abstract class representing a building in the game.
 * It contains common properties and methods for all buildings.
 * Specific building types (e.g., Faculty, Rectorate) will extend this class.
 */
public abstract class Building implements Json.Serializable {
    private transient Image image;
    private String texturePath;
    protected String name;
    protected String info;
    protected int buildCost;
    protected int bill;
    protected int constructionTime;
    protected int studentSatisfactionPoint;
    protected int universityReputationPoint;
    protected int income;

    // Constructor for the Building class
    // Initializes the building's name, cost, and bill.
    public Building(String name, int cost, int bill,int studentSatisfactionPoint,int universityReputationPoint) {
        this.info = "No additional information";
        this.constructionTime = 0;
        this.name = name;
        this.buildCost = cost;
        this.bill = bill;
        this.studentSatisfactionPoint = studentSatisfactionPoint;
        this.universityReputationPoint = universityReputationPoint;
    }

    //An abstract method to get the building's information.
    public abstract String getInfo();

    // Getter methods for the building's properties
    public String getName(){
        return this.name;
    }

    public int getCost(){
        return this.buildCost;
    }

    public int getBill(){
        return this.bill;
    }
    public int getConstructionTime(){
        return this.constructionTime;
    }
    //Setter methods for the building's properties
    public void setConstructionTime(int time){
        this.constructionTime = time;
    }
    public void setInfo(String info){
        this.info = info;
    }
    public int getStudentSatisfactionPoint() {
        return studentSatisfactionPoint;
    }

    public int getIncome(){
        return this.income;
    }

    @Override
    public void write(Json json) {
        json.writeValue("type", this.getClass().getName());
        json.writeValue("name", name);
        json.writeValue("info", info);
        json.writeValue("buildCost", buildCost);
        json.writeValue("bill", bill);
        json.writeValue("constructionTime", constructionTime);
        json.writeValue("studentSatisfactionPoint", studentSatisfactionPoint);
        json.writeValue("universityReputationPoint", universityReputationPoint);
        json.writeValue("income", income);
        json.writeValue("texturePath", texturePath); // ✅ EKLENDİ
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        this.name = jsonData.getString("name");
        this.info = jsonData.getString("info");
        this.buildCost = jsonData.getInt("buildCost");
        this.bill = jsonData.getInt("bill");
        this.constructionTime = jsonData.getInt("constructionTime");
        this.studentSatisfactionPoint = jsonData.getInt("studentSatisfactionPoint");
        this.universityReputationPoint = jsonData.getInt("universityReputationPoint");
        this.income = jsonData.getInt("income");
        this.texturePath = jsonData.getString("texturePath"); // ✅ EKLENDİ
    }

    public Image getImage() {
        if (image == null) {
            initializeVisuals();
        }
        return image;
    }
    public void initializeVisuals() {
        if (texturePath != null && image == null) {
            Texture texture = new Texture(Gdx.files.internal(texturePath));
            image = new Image(texture);
        }
    }
}
