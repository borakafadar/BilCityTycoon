package io.github.bilcitytycoon.Save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import io.github.bilcitytycoon.*;
import io.github.bilcitytycoon.Screens.GameScreen;

/**
 * Handles saving and loading of the BilCity Tycoon game state using JSON serialization.
 * Uses libGDX's JSON utility to write and read game data.
 */
public class SaveLoad {

    /** Reference to the current game state */
    private BilCityTycoonGame game;

    /** Reference to the main application for screen transitions */
    private Main mainGame;

    /** JSON utility helper with class tags and formatting options */
    private Json jsonHelper;

    /**
     * Constructs a SaveLoad handler with the current game and main instance.
     * Sets up the JSON helper and registers class tags for polymorphic deserialization.
     *
     * @param game Current game instance to be saved or loaded into
     * @param mainGame Reference to the main game class for screen control
     */
    public SaveLoad(BilCityTycoonGame game, Main mainGame){
        this.game = game;
        this.mainGame = mainGame;

        jsonHelper = new Json();
        jsonHelper.setOutputType(JsonWriter.OutputType.json);
        jsonHelper.setTypeName("type");
        jsonHelper.setIgnoreUnknownFields(true);

        // Register fully qualified class names and aliases
        jsonHelper.addClassTag("io.github.bilcitytycoon.Faculty", Faculty.class);
        jsonHelper.addClassTag("io.github.bilcitytycoon.OtherBuilding", OtherBuilding.class);
        jsonHelper.addClassTag("Faculty", Faculty.class);
        jsonHelper.addClassTag("OtherBuilding", OtherBuilding.class);
    }

    /**
     * Saves the current game state to a local file as JSON.
     * File name is generated based on the player's save name.
     */
    public void saveGame(){
        FileHandle saveFileHandle = Gdx.files.local(SaveManager.generateSaveFilePath(game.getPlayer().getSaveFileName()));
        String jsonString = jsonHelper.toJson(game);
        jsonString = jsonHelper.prettyPrint(jsonString);
        saveFileHandle.writeString(jsonString, false);
    }

    /**
     * Loads a saved game from a local JSON file.
     * Reconstructs the game object and sets the screen to GameScreen.
     *
     * @param fileName Path of the file to load from
     */
    public void loadGame(String fileName){
        FileHandle saveFileHandle = Gdx.files.local(fileName);
        if(saveFileHandle.exists()){
            String jsonString = saveFileHandle.readString();

            game = jsonHelper.fromJson(BilCityTycoonGame.class, jsonString);
            game.getMap().restoreGridFromPlacedBuildings();
            mainGame.setScreen(new GameScreen(mainGame, game));
            System.out.println("Loaded save file: " + fileName);
        } else {
            System.out.println("No save file found");
        }
    }
}
