package io.github.bilcitytycoon;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Represents a placed building on the game map.
 * Contains both the building object and its position/size in grid units.
 * Supports JSON serialization for saving/loading map state.
 */
public class PlacedBuilding implements Json.Serializable {

    /**
     * The building instance placed on the map.
     */
    public Building building;

    /**
     * X-coordinate of the top-left corner of the building in the grid.
     */
    public int x;

    /**
     * Y-coordinate of the top-left corner of the building in the grid.
     */
    public int y;

    /**
     * Width (in grid units) the building occupies.
     */
    public int width;

    /**
     * Height (in grid units) the building occupies.
     */
    public int height;

    /**
     * Default constructor required for JSON deserialization.
     */
    public PlacedBuilding() {} // JSON için boş constructor

    /**
     * Constructs a new placed building with its grid position and size.
     *
     * @param building The building object being placed.
     * @param x X-coordinate on the grid.
     * @param y Y-coordinate on the grid.
     * @param width Width in grid tiles.
     * @param height Height in grid tiles.
     */
    public PlacedBuilding(Building building, int x, int y, int width, int height) {
        this.building = building;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Serializes the placed building to JSON format.
     */
    @Override
    public void write(Json json) {
        json.writeValue("building", building);
        json.writeValue("x", x);
        json.writeValue("y", y);
        json.writeValue("width", width);
        json.writeValue("height", height);
    }

    /**
     * Deserializes a placed building from JSON data.
     *
     * @param json The Json utility instance.
     * @param jsonData The JSON data to read from.
     */
    @Override
    public void read(Json json, JsonValue jsonData) {
        this.building = json.readValue(Building.class, jsonData.get("building"));
        this.x = jsonData.getInt("x");
        this.y = jsonData.getInt("y");
        this.width = jsonData.getInt("width");
        this.height = jsonData.getInt("height");
    }
}
