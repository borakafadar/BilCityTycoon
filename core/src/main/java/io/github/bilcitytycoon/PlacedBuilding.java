package io.github.bilcitytycoon;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class PlacedBuilding implements Json.Serializable {
    public Building building;
    public int x, y, width, height;

    public PlacedBuilding() {} // JSON için boş constructor

    public PlacedBuilding(Building building, int x, int y, int width, int height) {
        this.building = building;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void write(Json json) {
        json.writeValue("building", building);
        json.writeValue("x", x);
        json.writeValue("y", y);
        json.writeValue("width", width);
        json.writeValue("height", height);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        this.building = json.readValue(Building.class, jsonData.get("building"));
        this.x = jsonData.getInt("x");
        this.y = jsonData.getInt("y");
        this.width = jsonData.getInt("width");
        this.height = jsonData.getInt("height");
    }
}

