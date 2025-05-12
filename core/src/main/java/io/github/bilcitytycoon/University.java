package io.github.bilcitytycoon;

public abstract class University implements Comparable<University>{
    public int leaderboardRanking;
    public String name;
    //this is the current reputation points
    public final int baseReputationPoints; //from facilities, events
    public int universityReputationPoint;
    public int studentSatisfactionRate;


    //The constructor
    public University(String name, int baseRep,int satisfaction) {
        this.name = name;
        this.baseReputationPoints = baseRep;
        this.universityReputationPoint = baseRep;
        this.studentSatisfactionRate = satisfaction;

    }
    //Getter methods
    public int getRanking(){
        return leaderboardRanking;
    }

    public int getBaseReputation() {
        return baseReputationPoints;
    }
    public String getName(){
        return this.name;
    }
    public int getUniversityReputationPoint(){
        return this.universityReputationPoint;
    }
    public int getStudentSatisfactionRate(){
        return this.studentSatisfactionRate;
    }

    public void setRankings(int rank){
        this.leaderboardRanking = rank;
    }


    @Override
    public int compareTo(University u) {
        return Integer.compare(u.universityReputationPoint, this.universityReputationPoint);
    }

    //used by leaderboard.updateRanking()
    public void setUniversityReputationPoints(int point) {
       this.universityReputationPoint = point;
    }

    //This might be changed
    @Override
    public String toString(){
        return String.format("#%d %s  (Rep=%d, Sat=%d%%)",
        leaderboardRanking, name, universityReputationPoint, studentSatisfactionRate);
    }
}
