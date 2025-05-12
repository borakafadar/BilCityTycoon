package io.github.bilcitytycoon;

import com.badlogic.gdx.Game;
import io.github.bilcitytycoon.Screens.Store.FacultiesStoreScreen;
import io.github.bilcitytycoon.Screens.Store.StoreScreen;
import io.github.bilcitytycoon.Screens.WelcomeScreen;


//using game because it is easier to set different screens
public class Main extends Game {


   @Override
   public void create() {
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
