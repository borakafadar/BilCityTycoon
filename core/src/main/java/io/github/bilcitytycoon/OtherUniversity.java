package io.github.bilcitytycoon;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a bot university in the game.
 * Required for the Leaderboard class.
 */
public class OtherUniversity extends University {

    /**
     * Shared random number generator for all AI universities.
     */
    private static final Random random = new Random();

    /**
     * Maximum fluctuation allowed in monthly student satisfaction.
     */
    private static final int MAX_MONTHLY_CHANGE = 5;

    /**
     * Percentage chance per month to make an investment.
     */
    private static final int INVESTMENT_CHANCE = 15;

    /**
     * Maximum amount that can be invested in a single action.
     */
    private static final int MAX_INVESTMENT = 1000;

    /**
     * Tracks how many months have passed since the last investment.
     */
    private int monthsSinceLastInvestment;

    /**
     * List of the most recent types of investments made.
     */
    private ArrayList<String> recentInvestments;

    /**
     * Constructs a new AI university with initial reputation and satisfaction.
     *
     * @param name Name of the university.
     * @param universityReputationPoint Initial reputation.
     * @param studentSatisfactionRate Initial satisfaction rate.
     */
    public OtherUniversity(String name, int universityReputationPoint, int studentSatisfactionRate) {
        super(name, universityReputationPoint, studentSatisfactionRate, 0, 0, 0);
        this.leaderboardRanking = 0; // Default ranking
        this.monthsSinceLastInvestment = 0;
        this.recentInvestments = new ArrayList<>();
    }

    /**
     * Default constructor used for deserialization or fallback cases.
     */
    public OtherUniversity() {
        super("other university", 0, 0, 0, 0, 0);
    }

    /**
     * Simulates one month of activity for the AI university.
     * This includes satisfaction changes, income, investments, and reputation updates.
     */
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
     * Simulates income and expenses based on current reputation and fluctuations.
     */
    private void simulateMonthlyIncome() {
        // Base income calculation
        int baseIncome = universityReputationPoint * 100;

        // Add random fluctuation (-10% to +10%)
        double fluctuation = 0.9 + (random.nextDouble() * 0.2);
        int actualIncome = (int) (baseIncome * fluctuation);

        // Add income
        moneyHandler.updateIncome(actualIncome);

        // Simulate expenses (60-80% of income)
        int expenses = (int) (actualIncome * (0.6 + random.nextDouble() * 0.2));
        moneyHandler.updateExpense(expenses);
    }

    /**
     * Determines whether to invest in upgrades this month and applies effects.
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
     * Updates university reputation based on satisfaction,
     * recent investments, and a small random event effect.
     */
    private void updateAIReputation() {
        // Base reputation from satisfaction
        int newReputation = (int) (studentSatisfactionRate * 0.7);

        // Bonus from recent investments
        newReputation += recentInvestments.size() * 2;

        // Random event impact (-5 to +5)
        newReputation += random.nextInt(11) - 5;

        // Ensure reputation stays within bounds
        newReputation = Math.max(10, Math.min(100, newReputation));

        setUniversityReputationPoints(newReputation);
    }

    /**
     * Randomly selects an investment type from a predefined list.
     *
     * @return The chosen investment type as a string.
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
     * Returns a copy of the list of recent investments.
     *
     * @return List of investment type names.
     */
    public ArrayList<String> getRecentInvestments() {
        return new ArrayList<>(recentInvestments);
    }

    /**
     * Reacts to the human player's rank by possibly triggering extra investment efforts.
     *
     * @param playerRanking The player's current leaderboard rank.
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

    /**
     * Returns a formatted string for debugging/displaying the university's current state.
     */
    @Override
    public String toString() {
        return String.format("%s [AI] #%d (Rep=%d, Sat=%d%%, Income=%d)",
            name, leaderboardRanking, universityReputationPoint,
            studentSatisfactionRate, moneyHandler.getNetIncome());
    }
}
