package io.github.bilcitytycoon;

public abstract class University implements Comparable<University>{
    protected int leaderboardRanking;
    protected String name;
    //this is the current reputation points
    protected final int baseReputationPoints; //from facilities, events
    protected int universityReputationPoint;
    protected int studentSatisfactionRate;
    protected final Leaderboard leaderboard;

    //The constructor
    protected University(String name, int baseRep,int satisfaction, Leaderboard leaderboard) {
        this.name = name;
        this.baseReputationPoints = baseRep;
        this.universityReputationPoint = baseRep;
        this.studentSatisfactionRate = satisfaction;
        this.leaderboard = leaderboard;
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
    public Leaderboard getLeaderboard(){
        return this.leaderboard;
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
