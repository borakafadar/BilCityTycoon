package io.github.bilcitytycoon;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import io.github.bilcitytycoon.Screens.EndingScreen;
import io.github.bilcitytycoon.Screens.GameScreen;
import io.github.bilcitytycoon.Screens.Store.FacultiesStoreScreen;
import io.github.bilcitytycoon.Screens.Store.StoreScreen;
import io.github.bilcitytycoon.Screens.WelcomeScreen;

/**
 * The main entry point for the game. Extends LibGDX's Game class to allow screen management.
 * Handles music, sound effects, and sets the initial screen.
 */
public class Main extends Game {

    /**
     * Sound effect played on clicks.
     */
    public Sound sound;

    /**
     * Background music played in the game.
     */
    public Music music;

    /**
     * Initializes the game: loads music/sound, starts playing background music,
     * and sets the initial screen to WelcomeScreen.
     * All the other things happen in the Screens.
     */
    @Override
    public void create() {
        music = Gdx.audio.newMusic(Gdx.files.internal("ncssongs.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        sound = Gdx.audio.newSound(Gdx.files.internal("mouseClick.mp3"));
        sound.setVolume(1, 0.5f);
        //First screen
        setScreen(new WelcomeScreen(this));
    }

    /**
     * Renders the game.
     */
    @Override
    public void render() {
        super.render(); // Call parent class to propagate rendering to the active screen
    }

    /**
     * Releases resources when the application is closed.
     * (Currently empty, but can be used to dispose music, sound, or screens)
     */
    @Override
    public void dispose() {
        super.dispose();
        this.dispose();
        this.getScreen().dispose();
    }
}
