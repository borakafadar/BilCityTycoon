package io.github.bilcitytycoon;


import java.util.ArrayList;

public class Player extends University {
    public int coin;
    public String name;
    public int universityReputationPoint;
    public int studentSatisfactionRate;
    public ArrayList<Building> buildings;
    public int studentCount;
    public int dormOccupancy;
    public int dormCapacity;

    public Player(String name, int universityReputationPoint, int studentSatisfactionRate, int studentCount ,int dormOccupancy, int dormCapacity)
    {
        super(name, universityReputationPoint, studentSatisfactionRate,studentCount,dormOccupancy,dormCapacity); // <-- bunu ekledik
        this.name = name;
        this.universityReputationPoint = universityReputationPoint;
        this.studentSatisfactionRate = studentSatisfactionRate;
        this.buildings = new ArrayList<>();
    }
    public Player(){
        super("default",0,0,0,0,0);
    }


    public int getCoin() {
        return coin;
    }
    public void setCoin(int newCoin) {
        this.coin = newCoin;
    }
    public String getName() {
        return name;
    }
    public int getUniversityReputationPoint()
    {
        return universityReputationPoint;
    }

    public void addStudenSatisfactionPoint(int stuSatiFaction){
        this.studentSatisfactionRate += stuSatiFaction;
    }
    public void setUniversityReputationPoint(int newUniversityReputationPoint){
        this.universityReputationPoint = newUniversityReputationPoint;
    }
    public int getStudentSatisfactionRate(){
        return studentSatisfactionRate;
    }
    public void setStudentSatisfactionRate(int newStudentSatisfactionRate){
        this.studentSatisfactionRate = newStudentSatisfactionRate;
    }
    public ArrayList<Building> getBuildings(){
        return buildings;
    }

    public void addBuilding(Building building){
        //TODO
        this.buildings.add(building);
    }


    public int getReputationPoints() {
        return this.universityReputationPoint;
    }

    public int getSatisfactionRate() {
        return this.studentSatisfactionRate;
    }
    public int getStudentCount() {
        return this.studentCount;
    }
    public int getDormOccupancy() {
        return this.dormOccupancy;
    }
    public int getDormCapacity() {
        return this.dormCapacity;
    }

    public String getSaveFileName(){
        String[] words = getName().split(" ");
        StringBuilder saveFileName = new StringBuilder();
        for (String str : words){
            saveFileName.append(str.toLowerCase()).append("-");
        }
        return saveFileName.toString();
    }

    public void setName(String playerName) {
        this.name = playerName;
    }
}
