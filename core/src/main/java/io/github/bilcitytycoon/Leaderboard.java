package io.github.bilcitytycoon;

import java.util.*;

/**
 * Backend model for managing an 80-entry leaderboard
 * (the human Player + 79 AI bots), sorted by Reputation point which is affected by Satisfaction points.
 * the computation is rep = satisfactonRate x (some ratio that will be determined later)
 */
public class Leaderboard {

    /**
     * All universities (player + AI).
     */
    public ArrayList<University> rankings = new ArrayList<>();

    /**
     * The player's university instance.
     */
    public Player player;

    /**
     * Random instance used for shuffling and value generation.
     */
    public Random random = new Random();

    /**
     * Multiplier used to convert satisfaction into reputation.
     */
    public static final int SATISFACTION_MULTIPLIER = 70; // THIS CAN BE ALTERED

    /**
     * Preset names for AI bots.
     */
    public static final String[] BOT_NAMES = {
        "Kanzi", "Delibas", "Undem", "Simsir", "Kafadar", "Yildirim", "Zeyno", "Zurna",
        "Sekman", "Unalan", "Yildiran", "Simsek", "Uranus", "Titanium", "Kayip Esyalar", "Bridgestone",
        "Opel", "Ape", "Ne Gu", "Burnming", "Cevrimici", "Acim", "Improvements", "Nuray",
        "Emale", "Sleep", "String", "Continue", "Codding", "Libbear", "Kutup", "Nurgul",
        "Yanlislaf", "Vurmaver", "Orangutann", "CoDDesigning", "Brimley", "Vireon", "Calchester", "Ironwell",
        "Stormmere", "Crestmoor", "Marenth", "Eloria", "Westford", "Myndale", "Duskmoor", "Halbridge",
        "Venthor", "Silvermark", "Brighthelm", "Norwyn", "Galderan", "Eastmere", "Draymar", "Borewyn",
        "Trivent", "Runestone", "Wyrnfall", "Ashmor", "Cindergate", "Starwyn", "Frostmoor", "Veriton",
        "Greymark", "Tarnhelm", "Quanthelm", "Blackmere", "Ebonridge", "Cloudmere", "Highvale", "Bronzefield",
        "Glenhaven", "Thornford", "Solwyn", "Vandale", "Mistbrook", "Ismere", "Redwyn", "Goldbarrow",
        "Newwyn", "Maveth", "Stonewyn", "Westoria", "Rynmere", "Netherford", "Vortexa", "Hawkmere",
        "Graveth", "Skyreach", "Cypresson", "Kryden", "Lorewyn", "Zenmere", "Altmore", "Brighthall",
        "Vistaryn", "Oakspire", "Stormbreal"
    };

    /**
     * Initialize with the human player; generate 79 bots and compute initial ranks.
     * @param player the Player instance representing the user's university
     */
    public Leaderboard(Player player) {
        this.player = player;
        rankings.add(player);
        generateBots(79);
        updateRanking();
    }

    /**
     * Default constructor for empty initialization (e.g. for loading).
     *
     */
    public Leaderboard() {
    }

    // generate N AI universities.
    private void generateBots(int count) {
        ArrayList<String> names = new ArrayList<>(Arrays.asList(BOT_NAMES));
        Collections.shuffle(names, random);
        for (int i = 0; i < count && i < names.size(); i++) {
            String name = names.get(i) + " University";
            int baseRep = (random.nextInt(481) + 20) * 10;  // 200–5000
            int satisfaction = random.nextInt(81) + 20;     // 20–100%
            rankings.add(new OtherUniversity(name, baseRep, satisfaction));
        }
    }

    /**
     * 1) Overwrite each uni’s repPoints = satRate × SAT_MULTIPLIER
     * 2) Sort in-place by natural order (uni.compareTo → rep descending)
     * 3) Reassign 1-based ranks
     */
    public void updateRanking() {
        for (University u : rankings) {
            int sat = u.getStudentSatisfactionRate();
            u.setUniversityReputationPoints(sat * SATISFACTION_MULTIPLIER);
        }

        // Efficient in-place sort (TimSort under the hood, O(n log n))
        Collections.sort(rankings);

        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRankings(i + 1);
        }
    }

    /**
     * @return A new list of universities sorted by reputation in descending order.
     */
    public ArrayList<University> getByReputation() {
        ArrayList<University> sorted = new ArrayList<>(rankings);
        sorted.sort(Comparator
            .comparingInt(University::getUniversityReputationPoint)
            .reversed()
        );
        return sorted;
    }

    /** @return the player’s current Reputation rank (1 = top). */
    public int getPlayerReputationRank() {
        updateRanking();
        return player.getRanking();
    }

    /** @return the raw list of all universities (in current sorted order). */
    public ArrayList<University> getAllUniversities() {
        return new ArrayList<>(rankings);
    }

    /** Add a new university (e.g. for custom or loaded data) and re-rank. */
    public void addUniversity(University uni) {
        rankings.add(uni);
        updateRanking();
    }

    /** Bulk-add universities (e.g. from a save file) and re-rank. */
    public void addUniversities(Collection<University> unis) {
        rankings.addAll(unis);
        updateRanking();
    }
}
