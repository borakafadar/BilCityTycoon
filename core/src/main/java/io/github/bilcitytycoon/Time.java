package io.github.bilcitytycoon;

/**
 * Manages in-game time based on real-time passage.
 * Handles academic calendar, month/semester transitions, and income updates.
 */
public class Time {

    /** The last in-game day when the year change was checked. */
    private int lastYearCheckedDay = 0;

    /** The player whose time progression is tracked. */
    public Player player;

    /** Reference to the player's money handler, used for collecting monthly income. */
    public MoneyHandler moneyHandler;

    /** Current month index within the semester [0–3]. */
    public int monthIndex = 0; //starts the game with month index [0-3]

    /** Start year of the academic calendar (e.g., 2024 means 2024–2025). */
    public int startYear = 2024; //sets the year to 2024

    /** Whether it's currently the Fall semester. If false, it's Spring. */
    public boolean isFallSemester = true;

    /** Timestamp of the last update in real-time milliseconds. */
    public long lastUpdatedTime; //saves the last update of time

    /** Default real-time duration of an in-game month (2 minutes). */
    public long definedMonthDurationMillis = 60 * 1000 * 2; //default month duration

    /** Current duration of an in-game month. Can be modified. */
    public long monthDurationMillis = definedMonthDurationMillis; //month duration to be altered in the future

    /** Real-time duration of a single in-game day (default 10 seconds). */
    public long definedDayDurationMillis = 10 * 1000;

    /** Total number of in-game days passed since the start of the game. */
    public int totalDaysPlayed = 1;

    /** Accumulated real-time passed (used to calculate day progression). */
    public long inGameTimePlayed = 0;

    /**
     * Constructs a Time instance and sets the initial update timestamp.
     */
    public Time(){
        this.lastUpdatedTime = System.currentTimeMillis();
    }

    /**
     * Should be called regularly to update in-game time based on real-time passed.
     * Handles in-game day, month, and semester transitions.
     */
    public void updateTime(){
        long currentTime = System.currentTimeMillis();
        long delta = currentTime - lastUpdatedTime;
        inGameTimePlayed += delta;
        lastUpdatedTime = currentTime; // her çağrıda güncellenmeli

        int daysPassed = (int) (inGameTimePlayed / definedDayDurationMillis);
        totalDaysPlayed += daysPassed;
        inGameTimePlayed %= definedDayDurationMillis;

        if (totalDaysPlayed >= lastYearCheckedDay + 120) {
            lastYearCheckedDay = totalDaysPlayed;
            isFallSemester = !isFallSemester;
            if (isFallSemester) {
                startYear++;
            }
        }

        if (delta >= monthDurationMillis) {
            int monthsPassed = (int) (delta / monthDurationMillis);
            for (int i = 0; i < monthsPassed; i++) {
                advanceMonth();
            }
        }
    }

    /**
     * Returns the current player.
     * @return the player instance
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Advances to the next month, updating semester/year and collecting income.
     */
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

    /**
     * Speeds up the in-game time by halving the duration of a month and day.
     */
    public void speedUpTime(){
        if(monthDurationMillis == definedMonthDurationMillis){
            this.monthDurationMillis = definedMonthDurationMillis / 2;
            this.definedDayDurationMillis = 5000; // 5 saniye
        }
    }

    /**
     * Resets the in-game time speed back to default values.
     */
    public void resetTimeSpeed(){
        if(monthDurationMillis == definedMonthDurationMillis / 2){
            this.monthDurationMillis = definedMonthDurationMillis;
            this.definedDayDurationMillis = 10000; // 10 saniye
        }
    }

    /**
     * @return Total in-game days played by the player.
     */
    public int getTotalDaysPlayed() {
        return totalDaysPlayed;
    }

    /**
     * @return Current month number in the semester (1 to 4).
     */
    public int getMonthInSemester() {
        return monthIndex + 1;
    }

    /**
     * @return The name of the current semester ("Fall" or "Spring").
     */
    public String getSemesterName(){
        return isFallSemester ? "Fall" : "Spring";
    }

    /**
     * @return A string representing the academic year, e.g., "2024-2025".
     */
    public String getAcademicYear(){
        int endYear = startYear + 1;
        return startYear + "-" + endYear;
    }

    /**
     * @return A formatted string describing the current game time.
     */
    public String getTime(){
        return getSemesterName() + " " + getAcademicYear() + ", Month " + getMonthInSemester() + ", Total Days Played: " + getTotalDaysPlayed();
    }

    /**
     * @return The current start year of the academic calendar.
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * @return The duration of one in-game day in real-time milliseconds.
     */
    public long getDefinedDayDurationMillis() {
        return definedDayDurationMillis;
    }

    /**
     * Sets the duration of one in-game day and resets accumulated time.
     * @param millis New real-time duration for one day.
     */
    public void setDayDurationMillis(long millis) {
        this.definedDayDurationMillis = millis;
        this.inGameTimePlayed = 0; // Eski kalan zaman sıfırlanmalı
    }

}
