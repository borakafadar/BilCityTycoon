package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Abstract class representing a building in the game.
 * Common attributes like cost, visual representation, and student satisfaction are stored here.
 * Specific building types (like Faculty, Rectorate) extend this class.
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

    /**
     * Constructs a new Building with given properties.
     *
     * @param name The name of the building.
     * @param cost The cost to construct the building.
     * @param bill The recurring cost of the building.
     * @param studentSatisfactionPoint Points that increase student satisfaction.
     * @param universityReputationPoint Points that increase university reputation.
     */
    public Building(String name, int cost, int bill, int studentSatisfactionPoint, int universityReputationPoint) {
        this.info = "No additional information";
        this.constructionTime = 0;
        this.name = name;
        this.buildCost = cost;
        this.bill = bill;
        this.studentSatisfactionPoint = studentSatisfactionPoint;
        this.universityReputationPoint = universityReputationPoint;
    }

    /**
     * Returns a description of the building.
     * Must be implemented by subclasses to provide building-specific information.
     *
     * @return The building's info string.
     */
    public abstract String getInfo();

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.buildCost;
    }

    public int getBill() {
        return this.bill;
    }

    public int getConstructionTime() {
        return this.constructionTime;
    }

    public void setConstructionTime(int time) {
        this.constructionTime = time;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStudentSatisfactionPoint() {
        return studentSatisfactionPoint;
    }

    public int getIncome() {
        return this.income;
    }

    /**
     * Serializes the building's properties to JSON.
     *
     * @param json The Json object used to write the data.
     */
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
        json.writeValue("texturePath", texturePath);
    }

    /**
     * Deserializes the building's properties from JSON.
     *
     * @param json      The Json instance.
     * @param jsonData  The JsonValue containing building data.
     */
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
        this.texturePath = jsonData.getString("texturePath");
    }

    /**
     * Returns the visual representation of the building.
     * Initializes the image if not already loaded.
     *
     * @return The Image object representing the building.
     */
    public Image getImage() {
        if (image == null) {
            initializeVisuals();
        }
        return image;
    }

    /**
     * Loads the texture and creates the image using the stored texture path.
     *
     */
    public void initializeVisuals() {
        if (texturePath != null && image == null) {
            Texture texture = new Texture(Gdx.files.internal(texturePath));
            image = new Image(texture);
        }
    }
}
