package io.github.bilcitytycoon;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

/**
 * Represents a map (campus) in the game, containing placed buildings, roads, and a visual layout.
 * Each map has a name, a 2D building grid, and road data for placement logic.
 * Implements JSON serialization for saving/loading.
 */
public class Map implements Json.Serializable {

    /**
     * List of buildings and their positions, used for serialization.
     */
    public ArrayList<PlacedBuilding> placedBuildings = new ArrayList<>();

    /**
     * 2D array representing the grid layout of buildings on the map.
     * Not serialized directly; restored via `placedBuildings`.
     */
    private transient Building[][] grid;

    /**
     * Name of the map, e.g., "North Campus".
     */
    private String mapName;

    /**
     * 2D boolean array marking whether a tile is a road.
     */
    private boolean[][] roads;

    /**
     * Group used to visually render building actors in the scene.
     */
    private transient Group buildingGroup = new Group();

    /**
     * List of buildings currently placed on the map.
     */
    public ArrayList<Building> buildings;

    /**
     * Default constructor for Map, creates a 20x15 grid named "North Campus".
     */
    public Map() {
        this.mapName = "North Campus";
        this.grid = new Building[20][15];
        this.roads = new boolean[20][15];
        this.buildingGroup = new Group();
        this.buildings = new ArrayList<>();
        this.placedBuildings = new ArrayList<>();
    }

    /**
     * Reconstructs the 2D building grid from the serialized placedBuildings list.
     * Also restores visuals by calling `initializeVisuals` on each building.
     */
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

    /**
     * Creates a map with custom name and dimensions.
     *
     * @param mapName The display name of the map.
     * @param width Number of columns in the grid.
     * @param height Number of rows in the grid.
     */
    public Map(String mapName, int width, int height) {
        this.mapName = mapName;
        grid = new Building[width][height];
        roads = new boolean[width][height];
        initializeDefaultRoads();
        Building[] arr = twoDTO1D(grid);
        buildings = new ArrayList<>();
        for (Building building : arr) {
            if (building != null) {
                buildings.add(building);
            }
        }
    }

    // Map.java içinde
    public void placeBuilding(Building building, int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                grid[i][j] = building;
            }
        }
        buildings.add(building);
        placedBuildings.add(new PlacedBuilding(building, x, y, width, height)); // 🔧 Yeni ek
    }

    /**
     * Sets up default road layout on the map.
     */
    private void initializeDefaultRoads() {
        addRoad(1, 5, 19, 1);
        addRoad(1, 5, 19, 1);
        addRoad(7, 1, 1, 10);
        addRoad(11, 1, 2, 10);
    }

    public Group getBuildingGroup() {
        return buildingGroup;
    }

    public Building[][] getGrid() {
        return grid;
    }

    public void setBuildingGroup(Group group) {
        this.buildingGroup = group;
    }

    public boolean isRoad(int x, int y) {
        if (x < 0 || y < 0 || x >= roads.length || y >= roads[0].length) return false;
        return roads[x][y];
    }

    public String getMapName() {
        return mapName;
    }

    /**
     * Adds a rectangular road area to the map.
     *
     * @param x Starting x coordinate.
     * @param y Starting y coordinate.
     * @param width Width of the road area.
     * @param height Height of the road area.
     */
    public void addRoad(int x, int y, int width, int height) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                roads[i][j] = true;
            }
        }
    }

    /**
     * Flattens a 2D grid of buildings into a 1D array.
     *
     * @param grid The 2D building array.
     * @return 1D array of all buildings in the grid.
     */
    private Building[] twoDTO1D(Building[][] grid) {
        Building[] buildings = new Building[grid.length * grid[0].length];
        int i = 0;
        for (Building[] row : grid) {
            for (Building building : row) {
                buildings[i] = building;
                i++;
            }
        }
        return buildings;
    }

    /**
     * Accesses a 2D coordinate from a 1D building array.
     */
    private Building access1Das2D(Building[] buildings, int x, int y) {
        return buildings[x * grid[0].length + y];
    }

    /**
     * Serializes the map data (excluding transient visual elements because libGDX cannot serialize them).
     * For saving save files.
     */
    @Override
    public void write(Json json) {
        json.writeValue("mapName", mapName);
        json.writeValue("roads", roads);
        json.writeValue("placedBuildings", placedBuildings);
    }

    /**
     * Deserializes the map data and reconstructs the visual grid.
     * For loading save files.
     */
    @Override
    public void read(Json json, JsonValue jsonData) {
        this.mapName = jsonData.getString("mapName");
        this.roads = json.readValue(boolean[][].class, jsonData.get("roads"));
        this.placedBuildings = json.readValue(ArrayList.class, PlacedBuilding.class, jsonData.get("placedBuildings"));
        this.grid = new Building[20][15]; // varsay
        this.buildings = new ArrayList<>();
        restoreGridFromPlacedBuildings();
    }

    // Uncomment when highlight feature is implemented
//    public void clearHighlights() {
//        highlightedAreas.clear();
//        for (int i = 0; i < highlightedCells.length; i++) {
//            for (int j = 0; j < highlightedCells[i].length; j++) {
//                highlightedCells[i][j] = false;
//            }
//        }
//    }
}
