package io.github.bilcitytycoon;

import java.util.ArrayList;
import java.util.Random;

public class OtherUniversity extends University {
    private static final Random random = new Random();
    private static final int MAX_MONTHLY_CHANGE = 5;
    private static final int INVESTMENT_CHANCE = 15; // Percentage chance to make investments
    private static final int MAX_INVESTMENT = 1000;

    private int monthsSinceLastInvestment;
    private ArrayList<String> recentInvestments;

    public OtherUniversity(String name, int universityReputationPoint, int studentSatisfactionRate) {
        super(name, universityReputationPoint, studentSatisfactionRate,0,0,0);
        this.leaderboardRanking = 0; // Default ranking
        this.monthsSinceLastInvestment = 0;
        this.recentInvestments = new ArrayList<>();
    }


    //Simulates monthly updates for bot university

    public void updateUniversity() {
        // Update satisfaction rate with random fluctuation
        int satisfactionChange = random.nextInt(MAX_MONTHLY_CHANGE * 2) - MAX_MONTHLY_CHANGE;
        adjustStudentSatisfactionRate(satisfactionChange);

        // Simulate monthly income
        simulateMonthlyIncome();

        // Consider making investments
        considerInvestments();

        // Update reputation points based on satisfaction and investments
        updateAIReputation();

        monthsSinceLastInvestment++;
    }

    /**
     * Simulates monthly income generation
     */
    private void simulateMonthlyIncome() {
        // Base income calculation
        int baseIncome = universityReputationPoint * 100;

        // Add random fluctuation (-10% to +10%)
        double fluctuation = 0.9 + (random.nextDouble() * 0.2);
        int actualIncome = (int)(baseIncome * fluctuation);

        // Add income
        moneyHandler.updateIncome(actualIncome);

        // Simulate expenses (60-80% of income)
        int expenses = (int)(actualIncome * (0.6 + random.nextDouble() * 0.2));
        moneyHandler.updateExpense(expenses);
    }

    /**
     * Considers making investments to improve university
     */
    private void considerInvestments() {
        if (monthsSinceLastInvestment >= 3 && random.nextInt(100) < INVESTMENT_CHANCE) {
            int investmentAmount = random.nextInt(MAX_INVESTMENT);
            String investmentType = getRandomInvestmentType();

            // Apply investment effects
            moneyHandler.updateExpense(investmentAmount);

            // Investment benefits
            switch (investmentType) {
                case "Research Facilities":
                    universityReputationPoint += 5;
                    break;
                case "Campus Improvements":
                    adjustStudentSatisfactionRate(8);
                    break;
                case "Academic Programs":
                    universityReputationPoint += 3;
                    adjustStudentSatisfactionRate(5);
                    break;
            }

            // Record investment
            recentInvestments.add(investmentType);
            if (recentInvestments.size() > 5) {
                recentInvestments.remove(0);
            }

            monthsSinceLastInvestment = 0;
        }
    }

    /**
     * Updates AI university's reputation based on various factors
     */
    private void updateAIReputation() {
        // Base reputation from satisfaction
        int newReputation = (int)(studentSatisfactionRate * 0.7);

        // Bonus from recent investments
        newReputation += recentInvestments.size() * 2;

        // Random event impact (-5 to +5)
        newReputation += random.nextInt(11) - 5;

        // Ensure reputation stays within bounds
        newReputation = Math.max(10, Math.min(100, newReputation));

        setUniversityReputationPoints(newReputation);
    }

    /**
     * Gets a random investment type
     */
    private String getRandomInvestmentType() {
        String[] investments = {
            "Research Facilities",
            "Campus Improvements",
            "Academic Programs"
        };
        return investments[random.nextInt(investments.length)];
    }

    /**
     * Get recent investment history
     */
    public ArrayList<String> getRecentInvestments() {
        return new ArrayList<>(recentInvestments);
    }

    /**
     * Responds to competition by adjusting strategy
     */
    public void respondToCompetition(int playerRanking) {
        if (leaderboardRanking > playerRanking) {
            // Increase investment chance if falling behind
            int extraInvestmentChance = random.nextInt(10);
            if (random.nextInt(100) < INVESTMENT_CHANCE + extraInvestmentChance) {
                considerInvestments();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s [AI] #%d (Rep=%d, Sat=%d%%, Income=%d)",
            name, leaderboardRanking, universityReputationPoint,
            studentSatisfactionRate, moneyHandler.getNetIncome());
    }
    public OtherUniversity(){
        super("other university",0,0,0,0,0);
    }
}
