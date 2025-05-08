package io.github.bilcitytycoon;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Manages a dynamic leaderboard of 80 universities (the player + 79 AI bots),
 * sorted either by Reputation Points or by Student Satisfaction Rate.
 *
 * Renders two small rank labels next to the BilCoins display on the HUD—
 * one for Reputation, one for Satisfaction.  Clicking either label
 * opens a scrollable pop-up showing the full ranking.
 *
 * @author Yaşar Delibaş
 * @version 1.0
 * @since 2025-04-30
 */
public class Leaderboard {
    //All universities (player + AI bots).
    private final ArrayList<Player> allUniversities;

    // Reference to the main game, for access to Stage & Skin.
    private final BilCityTycoonGame game;

    // The human player’s own university entry.
    private final Player player;

    /**
     * Constructs the leaderboard, adds the player and generates 79 bots.
     *
     * @param game   your main game instance
     * @param player the Player object representing the user's university
     */
    public Leaderboard(BilCityTycoonGame game, Player player) {
        this.game = game;
        this.player = player;
        this.allUniversities = new ArrayList<Player>();
        this.allUniversities.add(player);
        //createBots();
    }
    /**
     * Generates 79 “bot” universities with randomized Reputation and Satisfaction.
     * Uses a preset list of fantasy names.
     *
     */
    private void createBots() {
        String[] universityNames = {
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

        Random rd = new Random();
        /*for (String name : universityNames) {
            // Reputation: 200–5000 (in steps of 10)
            int reputation = (rd.nextInt(481) + 20) * 10;
            // Satisfaction: 20–100%
            int satisfaction = rd.nextInt(81) + 20;
            allUniversities.add(
                //new OtherUniversity(name, reputation, satisfaction, this)
            );
        }*/
    }

    /**
     * Returns a new ArrayList sorted by Reputation Points, descending.
     *
     * @return sorted copy of allUniversities
     */
    public ArrayList<Player> getByReputation() {
        ArrayList<Player> sorted = new ArrayList<>(allUniversities);
        // descending order: highest reputation first
        Collections.sort(sorted, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return p2.getReputationPoints() - p1.getReputationPoints();
            }
        });
        return sorted;
    }

  //Returns a new ArrayList sorted by Satisfaction Rate, descending.
    public ArrayList<Player> getBySatisfaction() {
        ArrayList<Player> sorted = new ArrayList<>(allUniversities);
        // descending order: highest satisfaction first
        Collections.sort(sorted, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return p2.getSatisfactionRate() - p1.getSatisfactionRate();
            }
        });
        return sorted;
    }

    /**
     * Computes the player's 1-based rank by Reputation.
     *
     * @return player's Reputation rank (1 = top)
     */
    public int getPlayerReputationRank() {
        ArrayList<Player> sorted = getByReputation();
        return sorted.indexOf(player) + 1;
    }

    /**
     * Computes the player's 1-based rank by Satisfaction.

    public int getPlayerSatisfactionRank() {
        ArrayList<Player> sorted = getBySatisfaction();
        return sorted.indexOf(player) + 1;
    }

    /**
     * Attaches two small rank Labels to the given HUD Table (next to BilCoins).
     * Clicking them opens the corresponding full leaderboard pop-up.
     *
     //* @param hudTable the libGDX Table containing your BilCoins display
     */
    /*public void attachToHUD(Table hudTable) {
        Skin skin = game.getSkin();
        Stage stage = game.getStage();

        // Reputation rank label
        final Label repLabel = new Label("#" + getPlayerReputationRank(), skin, "small");
        repLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showReputationPopup(stage, skin);
            }
        });
        hudTable.add(repLabel).padLeft(10);

        // Satisfaction rank label
        final Label satLabel = new Label("#" + getPlayerSatisfactionRank(), skin, "small");
        satLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showSatisfactionPopup(stage, skin);
            }
        });
        hudTable.add(satLabel).padLeft(5);
    }*/

   //Builds and displays the reputation leaderboard pop-up.
    private void showReputationPopup(Stage stage, Skin skin) {
        showPopup(stage, skin, getByReputation(), "Reputation Leaderboard");
    }

    //Builds and displays the satisfaction leaderboard pop-up.
    private void showSatisfactionPopup(Stage stage, Skin skin) {
        showPopup(stage, skin, getBySatisfaction(), "Satisfaction Leaderboard");
    }

    /**
     * Builds and displays a scrollable Window listing the given universities.
     *
     * @param stage    where to add the window
     * @param skin     UI skin for styling
     * @param list     pre-sorted ArrayList of Player entries
     * @param title    window title
     */
    private void showPopup(Stage stage, Skin skin, ArrayList<Player> list, String title) {
        Window win = new Window(title, skin);
        win.setModal(true);
        win.setMovable(true);
        win.pad(10);
        win.setSize(400, 500);
        win.setPosition(
            (stage.getWidth() - win.getWidth()) / 2,
            (stage.getHeight() - win.getHeight()) / 2
        );

        Table content = new Table(skin);
        int rank = 1;
        for (Player uni : list) {
            Label entry = new Label(
                String.format(
                    "#%d  %s  • Rep: %d  • Sat: %d%%",
                    rank++,
                    uni.getName(),
                    uni.getReputationPoints(),
                    uni.getSatisfactionRate()
                ),
                skin, "small"
            );
            content.row().pad(2);
            content.add(entry).left();
        }

        ScrollPane scroll = new ScrollPane(content, skin);
        scroll.setFadeScrollBars(false);
        win.add(scroll).expand().fill();
        win.row().padTop(10);

        TextButton close = new TextButton("Close", skin);
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                win.remove();
            }
        });
        win.add(close).center();

        stage.addActor(win);
    }

    // Exposes the raw list of all universities (player + bots).
    public ArrayList<Player> getAllUniversities() {
        return allUniversities;
    }
}
