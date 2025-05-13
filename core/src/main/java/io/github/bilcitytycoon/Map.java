package io.github.bilcitytycoon;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.io.Serializable;

public class Map implements Serializable {
    private Building[][] grid;
    private String mapName;
    private boolean[][] roads;
    private transient Group buildingGroup = new Group();


    public Map(String mapName, int width, int height){
        this.mapName = mapName;
        grid = new Building[width][height];
        roads = new boolean[width][height];
        initializeDefaultRoads();

    }

    public void placeInitialRoads() {
        for (int y = 0; y < grid[0].length; y++) {
            grid[10][y] = new Road(false);
        }

        for (int x = 0; x < grid.length; x++) {
            grid[x][7] = new Road(true);
        }
    }

    public void placeBuilding(Building building, int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                grid[i][j] = building;
            }
        }
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
}
