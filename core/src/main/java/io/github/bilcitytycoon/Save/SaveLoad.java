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
            jsonHelper.setTypeName("type"); // JSON'daki "type" key'ine göre sınıf tanı
            jsonHelper.setIgnoreUnknownFields(true);

            // Class tag’leri eksiksiz ekle:
            jsonHelper.addClassTag("io.github.bilcitytycoon.Faculty", Faculty.class);
            jsonHelper.addClassTag("io.github.bilcitytycoon.OtherBuilding", OtherBuilding.class);
            // (İsteğe bağlı: kısa adlar da ekleyebilirsin)
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
                game.getMap().restoreGridFromPlacedBuildings();
                mainGame.setScreen(new GameScreen(mainGame,game));
                System.out.println("Loaded save file: "+fileName);
            }else{
                System.out.println("No save file found");
            }
        }


    }
