package io.github.bilcitytycoon;


import java.util.ArrayList;

public class Player extends University {
    public MoneyHandler moneyHandler;
    public int coin;
    public String name;
    public int universityReputationPoint;
    public int studentSatisfactionRate;
    public int studentSatisfactionPoint; //10000
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
        this.studentSatisfactionPoint = this.studentSatisfactionRate*100;
        this.studentCount = studentCount;
        this.dormOccupancy = dormOccupancy;
        this.buildings = new ArrayList<>();
        this.moneyHandler = new MoneyHandler(1000); // 🎯 başlangıç bakiyesi veriyoruz
        this.coin = 1000; // 💰 coin değişkenini de senkronize et
    }
    public Player(){
        super("default",0,0,0,0,0);
    }


    public int getCoin() {
        return moneyHandler != null ? moneyHandler.getBalance() : coin;
    }
    public void setCoin(int newCoin) {
        this.coin = newCoin;
        if (moneyHandler != null) {
            moneyHandler.setBalance(newCoin); // ✅ artık hata yok
        }
    }
    public String getName() {
        return name;
    }
    public int getUniversityReputationPoint()
    {
        return universityReputationPoint;
    }

     void setUniversityReputationPoint(int newUniversityReputationPoint){
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

    public void calculateStudentSatisfactionRate(){
        this.studentSatisfactionRate = (int) (this.studentSatisfactionPoint / 10000.0 * 100);
    }
    public void addStudentSatisfactionPoint(int amount){
        this.studentSatisfactionPoint += amount;
    }
    public void addStudentCount(int amount){
        this.studentCount += amount;
    }
    public void addDormOccupancy(int amount){
        this.dormOccupancy += amount;
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

    public void decreaseStudentSatisfactionPointPerSecond(float deltaTime) {
        // Calculate how many points to decrease based on elapsed time
        // 1 point per second means we multiply deltaTime by 1
        int pointsToDecrease = (int) (deltaTime * 100f);

        // Ensure at least 1 point is decreased if any time has passed
        if (deltaTime > 0 && pointsToDecrease == 0) {
            pointsToDecrease = 1;
        }

        // Decrease the points
        this.studentSatisfactionPoint -= pointsToDecrease;

        // Ensure it doesn't go below 0
        if (this.studentSatisfactionPoint < 0) {
            this.studentSatisfactionPoint = 0;
        }

        // Update the studentSatisfactionRate based on the new point value
        calculateStudentSatisfactionRate();
    }

    public void constructBuilding(Building building){
        buildings.add(building);

    }
    public boolean isBankruptOfSatisfaction() {
        return this.studentSatisfactionPoint <= 0;
    }
    public void safeDecreaseStudentSatisfactionPoint(int amount) {
        this.studentSatisfactionPoint -= amount;
        if (this.studentSatisfactionPoint < 0) {
            this.studentSatisfactionPoint = 0;
        }
        calculateStudentSatisfactionRate();
    }


}
