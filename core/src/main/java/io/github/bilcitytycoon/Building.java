
package io.github.bilcitytycoon;

/*
 * Abstract class representing a building in the game.
 * It contains common properties and methods for all buildings.
 * Specific building types (e.g., Faculty, Rectorate) will extend this class.
 */
public abstract class Building {

    protected String name;
    protected String info;
    protected int buildCost;
    protected int bill;
    protected int constructionTime;
    protected int studentSatisfactionPoint;
    protected int universityReputationPoint;

    // Constructor for the Building class
    // Initializes the building's name, cost, and bill.
    public Building(String name, int cost, int bill,int studentSatisfactionPoint,int universityReputationPoint) {
        this.info = "No additional information";
        this.constructionTime = 0;
        this.name = name;
        this.buildCost = cost;
        this.bill = bill;
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
}
