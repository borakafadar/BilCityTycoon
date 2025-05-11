package io.github.bilcitytycoon;

import java.util.ArrayList;

//TODO: implement bot class for other universities
public class OtherUniversity extends University {
    private int leaderboardRanking;

    public OtherUniversity(String name, int universityReputationPoint, int studentSatisfactionRate, Leaderboard leaderboard){
        super(name, universityReputationPoint, studentSatisfactionRate, leaderboard);
        this.leaderboardRanking = 0; // Default ranking
    }
}
