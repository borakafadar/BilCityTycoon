package io.github.bilcitytycoon;

public class Map {
    private Building[] buildings;
    public Map(){
        buildings = new Building[27];
    }
    public void placeBuilding(Building b, int i){
        this.buildings[i] = b;
    }
}
