package io.github.bilcitytycoon;

import java.util.ArrayList;

//TODO: implement bot class for other universities
public class OtherUniversity extends University {
    public int leaderboardRanking;

    public OtherUniversity(String name, int universityReputationPoint, int studentSatisfactionRate){
        super(name, universityReputationPoint, studentSatisfactionRate);
        this.name = name;
        this.universityReputationPoint = universityReputationPoint;
        this.studentSatisfactionRate = studentSatisfactionRate;
    }
    public OtherUniversity(){
        super("other university",0,0);
    }
}
