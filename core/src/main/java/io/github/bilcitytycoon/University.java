package io.github.bilcitytycoon;

public abstract class University implements Comparable<University>{
    protected int leaderboardRanking;
    protected String name;
    protected int universityReputationPoint;
    protected int studentSatisfactionRate;
    protected Leaderboard leaderboard;

    public String getRanking(){
        return Integer.toString(this.leaderboardRanking);
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
    public int compareTo(University o) {
        //TODO
        return 0;
    }
}
