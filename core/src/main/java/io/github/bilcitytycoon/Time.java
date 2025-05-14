package io.github.bilcitytycoon;

public class Time {
    private int lastYearCheckedDay = 0;
    public Player player;
    public MoneyHandler moneyHandler;
    public int monthIndex = 0; //starts the game with month index [0-3]
    public int startYear = 2024; //sets the year to 2024
    public boolean isFallSemester = true;
    public long lastUpdatedTime; //saves the last update of time
    public long definedMonthDurationMillis = 60 * 1000 * 2; //default month duration
    public long monthDurationMillis = definedMonthDurationMillis;//month duration to be altered in the future
    public long definedDayDurationMillis = 10 * 1000;
    public int totalDaysPlayed = 1;
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
        lastUpdatedTime = currentTime; // her çağrıda güncellenmeli

        int daysPassed = (int) (inGameTimePlayed / definedDayDurationMillis);
        totalDaysPlayed += daysPassed;
        inGameTimePlayed %= definedDayDurationMillis;
        // mevcut gün hesaplamasından sonra
        if (totalDaysPlayed >= lastYearCheckedDay + 120) {
            lastYearCheckedDay = totalDaysPlayed;
            isFallSemester = !isFallSemester;
            if (isFallSemester) {
                startYear++;
            }
        }



        // yeni kontrol: kaç ay geçtiğini hesapla
        if (delta >= monthDurationMillis) {
            int monthsPassed = (int) (delta / monthDurationMillis);
            for (int i = 0; i < monthsPassed; i++) {
                advanceMonth();
            }
        }
    }

    public Player getPlayer(){
        return this.player;
    }


    //advances semester, if necessary, and month
    private void advanceMonth(){
        monthIndex++;
        moneyHandler.collectMonthlyIncome(player);
        totalDaysPlayed = 1;

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
            this.definedDayDurationMillis = 5000; // 5 saniye
        }
    }

    public void resetTimeSpeed(){
        if(monthDurationMillis == definedMonthDurationMillis / 2){
            this.monthDurationMillis = definedMonthDurationMillis;
            this.definedDayDurationMillis = 10000; // 10 saniye
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
    public int getStartYear() {
        return startYear;
    }
    public long getDefinedDayDurationMillis() {
        return definedDayDurationMillis;
    }
    public void setDayDurationMillis(long millis) {
        this.definedDayDurationMillis = millis;
        this.inGameTimePlayed = 0; // Eski kalan zaman sıfırlanmalı
    }

}
