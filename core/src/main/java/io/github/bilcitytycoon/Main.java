package io.github.bilcitytycoon;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import io.github.bilcitytycoon.Screens.GameScreen;
import io.github.bilcitytycoon.Screens.Store.FacultiesStoreScreen;
import io.github.bilcitytycoon.Screens.Store.StoreScreen;
import io.github.bilcitytycoon.Screens.WelcomeScreen;


//using game because it is easier to set different screens
public class Main extends Game {

    public Sound sound;
    public Music music;


   @Override
   public void create() {

       music = Gdx.audio.newMusic(Gdx.files.internal("ncssongs.mp3"));
       music.setLooping(true);
       music.setVolume(0.5f);
       music.play();

       sound = Gdx.audio.newSound(Gdx.files.internal("mouseClick.mp3"));
       sound.setVolume(1,0.5f);

       setScreen(new WelcomeScreen(this));


   }

    @Override
    public void render() {
        super.render(); // Call parent class to propagate rendering to the active screen
    }

    @Override
    public void dispose() {

    }
}
