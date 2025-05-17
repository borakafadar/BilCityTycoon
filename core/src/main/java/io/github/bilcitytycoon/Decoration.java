package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Represents a decorative object in the game such as trees, statues, or banners.
 * Decorations add visual or thematic value to the map, but typically have no gameplay effect.
 */
public class Decoration {
    /**
     * The name of the decoration.
     */
    public String name;

    /**
     * Info about the decoration.
     */
    public String info;

    /**
     * The cost to place the decoration.
     */
    public int cost;

    /**
     * Image of the decoration.
     */
    public transient Image image;

    /**
     * The time (in turns or ticks) it takes to build the decoration.
     * Not used yet.
     */
    public int buildTime;

    /**
     * The path to the image texture used by the decoration.
     */
    public String imagePath;

    /**
     * Full constructor to initialize all decoration attributes.
     *
     * @param name Name of the decoration.
     * @param cost Construction cost.
     * @param imagePath Path to the image asset.
     * @param info Description or tooltip text.
     * @param buildTime Time it takes to build.
     */
    public Decoration(String name, int cost, String imagePath, String info, int buildTime) {
        this.name = name;
        this.cost = cost;
        this.imagePath = imagePath;
        this.image = new Image(new Texture(Gdx.files.internal(imagePath)));
        this.info = info;
        this.buildTime = buildTime;
    }

    /**
     * Default constructor used during deserialization or testing.
     * libGDX's JSON class requires a constructor without any parameters, so this is just a placeholder.
     */
    public Decoration() {
        this.name = "yasar"; // Default placeholder name
    }

    public String getInfo() {
        return this.info;
    }

    public Image getImage() {
        return this.image;
    }

    public int getCost() {
        return this.cost;
    }

    public String getName() {
        return this.name;
    }

    public int getBuildTime() {
        return this.buildTime;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
