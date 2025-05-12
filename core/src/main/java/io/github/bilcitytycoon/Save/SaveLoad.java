package io.github.bilcitytycoon.Save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import io.github.bilcitytycoon.BilCityTycoonGame;
import io.github.bilcitytycoon.Main;
import io.github.bilcitytycoon.Player;
import io.github.bilcitytycoon.Screens.GameScreen;

public class SaveLoad {
    private BilCityTycoonGame game;
    private Main mainGame;

    public SaveLoad(BilCityTycoonGame game, Main mainGame){
        this.game = game;
        this.mainGame = mainGame;
    }


    public void saveGame(){
        FileHandle saveFileHandle = Gdx.files.local(SaveManager.generateSaveFilePath(game.getPlayer().getSaveFileName()));
        Json jsonHelper = new Json();
        jsonHelper.setOutputType(JsonWriter.OutputType.json);
        String jsonString = jsonHelper.toJson(game);
        jsonString = jsonHelper.prettyPrint(jsonString);
        saveFileHandle.writeString(jsonString,false);
    }

    public void loadGame(String fileName){
        Json jsonHelper = new Json();
        jsonHelper.setIgnoreUnknownFields(true);
        FileHandle saveFileHandle = Gdx.files.local(fileName);
        if(saveFileHandle.exists()){
            String jsonString = saveFileHandle.readString();

            game = jsonHelper.fromJson(BilCityTycoonGame.class,jsonString);
            mainGame.setScreen(new GameScreen(mainGame,game));
            System.out.println("Loaded save file: "+fileName);
        }else{
            System.out.println("No save file found");
        }

    }

}
