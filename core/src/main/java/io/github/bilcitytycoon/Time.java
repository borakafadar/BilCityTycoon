package io.github.bilcitytycoon;

public class Time {
    public int monthIndex = 0; //starts the game with month index [0-3]
    public int startYear = 2024; //sets the year to 2024
    public boolean isFallSemester = true;
    public long lastUpdatedTime; //saves the last update of time
    public long definedMonthDurationMillis = 5* 60 * 1000; //default month duration
    public long monthDurationMillis = definedMonthDurationMillis;//month duration to be altered in the future
    public long definedDayDurationMillis = 10 * 1000;
    public int totalDaysPlayed = 0;
    public long inGameTimePlayed = 0;

    public Time(){
        this.lastUpdatedTime = System.currentTimeMillis();
    }

    // is called regularly
    //advances in game time by tracking real world time and converting it
    public void updateTime(){
        long currentTime = System.currentTimeMillis();
        long delta = currentTime - lastUpdatedTime;
        inGameTimePlayed += delta;

        int daysPassed = (int) (inGameTimePlayed / definedDayDurationMillis);
        totalDaysPlayed += daysPassed;
        inGameTimePlayed %= definedDayDurationMillis;

        //advances the month if a full month has passed
        if(currentTime - lastUpdatedTime >= monthDurationMillis){
            advanceMonth(); //updates the time by advancing the current in game month
            lastUpdatedTime = currentTime;
        }
    }

    //advances semester, if necessary, and month
    private void advanceMonth(){
        monthIndex++;

        if(monthIndex == 4){
            monthIndex = 0;
            isFallSemester = !isFallSemester;

            if(isFallSemester){
                startYear++;
            }
        }

    }

    //makes months take half as long in real life
    public void speedUpTime(){
        if(monthDurationMillis == definedMonthDurationMillis){
            this.monthDurationMillis = definedMonthDurationMillis / 2;
        }
    }

    //reverts back the time speed to its initial form
    public void resetTimeSpeed(){
        if(monthDurationMillis == definedMonthDurationMillis / 2){
            this.monthDurationMillis = definedMonthDurationMillis;
        }
    }

    //returns how many in game days the players has played
    public int getTotalDaysPlayed() {
        return totalDaysPlayed;
    }

    //returns the current in game month number within the semester [1-4]
    public int getMonthInSemester() {
        return monthIndex + 1;
    }

    // returns fall or spring based on semester
    public String getSemesterName(){
        if(isFallSemester){
            return "Fall";
        }
        else{
            return "Spring";
        }
    }

    public String getAcademicYear(){
        int endYear = startYear + 1;
        return startYear + "-" + endYear;
    }

    public String getTime(){
        return getSemesterName() + " " + getAcademicYear() + ", Month " + getMonthInSemester() + ", Total Days Played: " + getTotalDaysPlayed();
    }

}
