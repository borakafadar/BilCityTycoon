    package io.github.bilcitytycoon.Save;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.files.FileHandle;
    import com.badlogic.gdx.utils.Json;
    import com.badlogic.gdx.utils.JsonWriter;
    import io.github.bilcitytycoon.*;
    import io.github.bilcitytycoon.Screens.GameScreen;

    public class SaveLoad {
        private BilCityTycoonGame game;
        private Main mainGame;
        private Json jsonHelper;

        public SaveLoad(BilCityTycoonGame game, Main mainGame){
            this.game = game;
            this.mainGame = mainGame;

            jsonHelper = new Json();
            jsonHelper.setOutputType(JsonWriter.OutputType.json);
            jsonHelper.setTypeName("type");
            jsonHelper.setIgnoreUnknownFields(true);

            jsonHelper.addClassTag("io.github.bilcitytycoon.Faculty", Faculty.class);
            jsonHelper.addClassTag("io.github.bilcitytycoon.OtherBuilding", OtherBuilding.class);
            jsonHelper.addClassTag("Leaderboard", Leaderboard.class);
            jsonHelper.addClassTag("Player", Player.class);
            jsonHelper.addClassTag("OtherUniversity", OtherUniversity.class);
            jsonHelper.addClassTag("Upgrade", Upgrade.class); // ✅

            jsonHelper.addClassTag("Faculty", Faculty.class);
            jsonHelper.addClassTag("OtherBuilding", OtherBuilding.class);
        }




        public void saveGame(){
            FileHandle saveFileHandle = Gdx.files.local(SaveManager.generateSaveFilePath(game.getPlayer().getSaveFileName()));
            String jsonString = jsonHelper.toJson(game);
            jsonString = jsonHelper.prettyPrint(jsonString);
            saveFileHandle.writeString(jsonString,false);
        }


        public void loadGame(String fileName){
            FileHandle saveFileHandle = Gdx.files.local(fileName);
            if(saveFileHandle.exists()){
                String jsonString = saveFileHandle.readString();

                game = jsonHelper.fromJson(BilCityTycoonGame.class,jsonString);

                game.setLeaderboard(new Leaderboard(game.getPlayer()));

                game.getMap().restoreGridFromPlacedBuildings();

                mainGame.setScreen(new GameScreen(mainGame,game));

                System.out.println("Loaded save file: "+fileName);
            } else {
                System.out.println("No save file found");
            }
        }



    }
