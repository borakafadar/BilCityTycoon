package io.github.bilcitytycoon;

public class Map {
    private Building[] buildings;
    public Map(){
        buildings = new Building[27];
    }
    public void placeBuilding(Building b, int i){
        this.buildings[i] = b;
    }

//    public void clearHighlights() {
//
//
//        // For example, if you're using a list to track highlighted areas:
//        highlightedAreas.clear();
//
//        for (int i = 0; i < highlightedCells.length; i++) {
//            for (int j = 0; j < highlightedCells[i].length; j++) {
//                highlightedCells[i][j] = false;
//            }
//        }
//    }

}
