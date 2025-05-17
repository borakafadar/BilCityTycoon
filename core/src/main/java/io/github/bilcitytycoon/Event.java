package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Random;

/**
 * Represents a random in-game event that affects gameplay.
 * Events can include disasters, donations, and protests, each with
 * an impact on player's BilCoins, and impact on player stats.
 */
public class Event {

    /**
     * Description of the event to be shown in the UI.
     */
    public String info;

    /**
     * Coin cost (or gain) associated with the event.
     */
    public int cost;

    /**
     * Character avatar shown alongside the event.
     */
    public transient Image avatar;

    /**
     * The internal path used to select an avatar.
     */
    public String imagePath;

    /**
     * Change to be applied to the student's satisfaction rate.
     */
    private int studentSatisfactionPoint;

    /**
     * Reference to the game instance for accessing player data.
     */
    private BilCityTycoonGame game;

    /**
     * Constructs a new Event instance.
     *
     * @param info Description text of the event.
     * @param cost Cost in BilCoins (can be negative for rewards).
     * @param game Reference to the game for updating state.
     * @param studentSatisfactionRateAffection Used for affecting how the student satisfaction rate has changed
     */
    public Event(String info, int cost, BilCityTycoonGame game, int studentSatisfactionRateAffection) {
        this.info = info;
        this.cost = cost;
        this.game = game;
    }

    /**
     * Triggers a fire disaster at the given building.
     *
     * @param b The building where the fire occurs.
     */
    public void burning(Building b) {
        this.info = "A fire broke out in " + b.getName() + "! Do you want to pay " + b.getCost() + " coins to put out the fire?";
        this.cost = b.getCost();
        this.studentSatisfactionPoint = -100;
        this.avatar = new Image(new Texture(Gdx.files.internal(getAvatar())));
        updatePlayer();
    }

    /**
     * Triggers a protest event that lowers satisfaction without financial cost.
     */
    public void protest() {
        this.info = "Students are protesting against the current state of the university! Try upgrading the buildings or adding new ones!";
        this.cost = 0;
        this.studentSatisfactionPoint = -60;
        this.avatar = new Image(new Texture(Gdx.files.internal(getAvatar())));
        updatePlayer();
    }

    /**
     * Triggers a donation event that adds coins and slightly increases satisfaction.
     */
    public void donation() {
        this.cost = -randomDonation();
        this.info = "A generous donor has donated " + -this.cost + " BilCoins to the university!";
        this.studentSatisfactionPoint = 10;
        this.avatar = new Image(new Texture(Gdx.files.internal(getAvatar())));
        updatePlayer();
    }

    public String getInfo() {
        return this.info;
    }

    public int getCost() {
        return this.cost;
    }

    /**
     * Generates a random donation amount between 50 and 149.
     *
     * @return The donation amount.
     */
    public int randomDonation() {
        Random rand = new Random();
        return rand.nextInt(50, 150);
    }

    /**
     * Randomly selects a path to a character avatar to represent the event.
     *
     * @return The avatar's relative file path.
     */
    public String getAvatar() {
        String[] avatars = {
            "character sprites/Bora_default.png",
            "character sprites/Eylul_default.png",
            "character sprites/Zeynel_default.png",
            "character sprites/Vural_default.png",
            "character sprites/Yasar_default.png"
        };
        Random rand = new Random();
        int randomIndex = rand.nextInt(avatars.length);
        return avatars[randomIndex];
    }

    /**
     * Applies the event effects to the player (cost and satisfaction).
     */
    public void updatePlayer() {
        this.game.getPlayer().getMoneyHandler().setBalance(
            this.game.getPlayer().getMoneyHandler().getBalance() - cost
        );
        this.game.getPlayer().setStudentSatisfactionRate(
            game.getPlayer().getStudentSatisfactionRate() + studentSatisfactionPoint
        );
    }

    /**
     * Selects a random building owned by the player.
     *
     * @return A random building.
     */
    public Building randomBuilding() {
        Random random = new Random();
        return game.getPlayer().getBuildings().get(
            random.nextInt(game.getPlayer().getBuildings().size())
        );
    }
}
