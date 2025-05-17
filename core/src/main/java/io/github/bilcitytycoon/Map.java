package io.github.bilcitytycoon;

import com.badlogic.gdx.scenes.scene2d.Group;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

public class Map implements Json.Serializable{
    public ArrayList<PlacedBuilding> placedBuildings = new ArrayList<>();
    private transient Building[][] grid;
    private String mapName;
    private boolean[][] roads;
    private transient Group buildingGroup = new Group();
    public ArrayList<Building> buildings;

    public Map() {
        this.mapName = "North Campus";
        this.grid = new Building[20][15];
        this.roads = new boolean[20][15];
        this.buildingGroup = new Group();
        this.buildings = new ArrayList<>();
        this.placedBuildings = new ArrayList<>();
    }
    public void restoreGridFromPlacedBuildings() {
        if (grid == null) grid = new Building[20][15];
        if (roads == null) roads = new boolean[20][15];
        if (placedBuildings == null) return;

        for (PlacedBuilding pb : placedBuildings) {
            if (pb == null || pb.building == null) continue;

            pb.building.initializeVisuals();

            for (int i = pb.x; i < pb.x + pb.width; i++) {
                for (int j = pb.y; j < pb.y + pb.height; j++) {
                    if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length) {
                        grid[i][j] = pb.building;
                    }
                }
            }

            if (!buildings.contains(pb.building)) {
                buildings.add(pb.building);
            }
        }

    }



    public Map(String mapName, int width, int height){
        this.mapName = mapName;
        grid = new Building[width][height];
        roads = new boolean[width][height];
        initializeDefaultRoads();
        Building[] arr = twoDTO1D(grid);
        buildings = new ArrayList<>();
        for(Building building : arr){
            if(building != null){
                buildings.add(building);
            }
        }

    }

    public void placeBuilding(Building building, int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                grid[i][j] = building;
            }
        }
        buildings.add(building);
        placedBuildings.add(new PlacedBuilding(building, x, y, width, height));
    }


    private void initializeDefaultRoads() {
        addRoad(1, 5, 19, 1);
        addRoad(1, 5, 19, 1);
        addRoad(7, 1, 1, 10);
        addRoad(11, 1, 2, 10);
    }
    public Group getBuildingGroup() {
        return buildingGroup;
    }

    public Building[][] getGrid(){
        return grid;
    }
    public void setBuildingGroup(Group group) {
        this.buildingGroup = group;
    }
    public boolean isRoad(int x, int y) {
        if (x < 0 || y < 0 || x >= roads.length || y >= roads[0].length) return false;
        return roads[x][y];
    }


    public String getMapName(){
        return mapName;
    }
    public void addRoad(int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                roads[i][j] = true;
            }
        }
    }

    private Building[] twoDTO1D(Building[][] grid){
        Building[] buildings = new Building[grid.length*grid[0].length];
        int i = 0;
        for(Building[] row : grid){
            for(Building building : row){
                buildings[i] = building;
                i++;
            }
        }
        return buildings;
    }

    private Building access1Das2D(Building[] buildings, int x, int y){
        return buildings[x*grid[0].length+y];
    }

    @Override
    public void write(Json json) {
        json.writeValue("mapName", mapName);
        json.writeValue("roads", roads);
        json.writeValue("placedBuildings", placedBuildings);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        this.mapName = jsonData.getString("mapName");
        this.roads = json.readValue(boolean[][].class, jsonData.get("roads"));
        this.placedBuildings = json.readValue(ArrayList.class, PlacedBuilding.class, jsonData.get("placedBuildings"));
        this.grid = new Building[20][15];
        this.buildings = new ArrayList<>();
        restoreGridFromPlacedBuildings();
    }

}
