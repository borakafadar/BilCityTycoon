package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.bilcitytycoon.BilCityTycoonGame;
import io.github.bilcitytycoon.Main;
import io.github.bilcitytycoon.Save.SaveLoad;
import io.github.bilcitytycoon.Save.SaveManager;

public class LoadGameScreen implements Screen {
    private FitViewport fitViewport;
    private StretchViewport stretchViewport; // for background
    private Stage backgroundStage;
    private Stage mainStage;
    private Skin skin;
    private BilCityTycoonGame game;
    private Main mainGame;
    private Label placeholderLabel;

    public LoadGameScreen(Main mainGame, Screen previousScreen) {
        this.game = game;
        this.mainGame = mainGame;
        this.mainStage = new Stage();
        this.fitViewport = new FitViewport(1920, 1080);

        this.stretchViewport = new StretchViewport(1366, 768);
        this.backgroundStage = new Stage();

        backgroundStage.setViewport(stretchViewport);
        mainStage.setViewport(fitViewport);


        skin = createSkin();


        Image panelBackground = new Image(new Texture(Gdx.files.internal("panelBackground.png")));
        backgroundStage.addActor(panelBackground);
        panelBackground.setSize(1920, 1080);


        Table rootTable = new Table();
        rootTable.setFillParent(true);


        Label titleLabel = new Label("Load Game", skin, "title-label");
        titleLabel.setAlignment(Align.center);
        rootTable.add(titleLabel).expandX().fillX().padTop(90).padBottom(50);
        rootTable.row();

        // Create placeholder for save files list
        Table saveFilesTable = createSavesTable();

        ScrollPane scrollPane = new ScrollPane(saveFilesTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setClamp(true);
        scrollPane.setOverscroll(false, true);

        rootTable.add(scrollPane).expand().fill();

        // Back button
        ImageButton backButton = new ImageButton(skin, "back-button");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(previousScreen);
            }
        });

        mainStage.addActor(rootTable);
        mainStage.addActor(backButton);
        backButton.setPosition(100, 920);
        backButton.setSize(100, 100);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);


        backgroundStage.act();
        stretchViewport.apply();
        backgroundStage.draw();


        mainStage.act();
        fitViewport.apply();
        mainStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width, height, true);
        stretchViewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        skin.dispose();
        mainStage.dispose();
        backgroundStage.dispose();
    }

    public Skin createSkin() {
        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(36, 1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16, 1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(13, 0);
        FreeTypeFontGenerator.FreeTypeFontParameter titleFontParameter = generateFontParameter(50, 2);

        BitmapFont bigFont = bigFontGenerator.generateFont(bigFontParameter);
        BitmapFont smallFont = bigFontGenerator.generateFont(smallFontParameter);
        BitmapFont smallestFont = bigFontGenerator.generateFont(smallestFontParameter);
        BitmapFont titleFont = bigFontGenerator.generateFont(titleFontParameter);

        Skin skin1 = new Skin();
        skin1.add("PressStart2P", bigFont);
        skin1.add("PressStart2P-small", smallFont);
        skin1.add("PressStart2P-smallest", smallestFont);
        skin1.add("PressStart2P-big", titleFont);

        skin1.addRegions(new TextureAtlas(Gdx.files.internal("skin1.atlas")));
        skin1.load(Gdx.files.internal("skin1.json"));

        return skin1;
    }

    private FreeTypeFontGenerator.FreeTypeFontParameter generateFontParameter(int size, int borderWidth) {
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = size;
        fontParameter.borderWidth = borderWidth;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.color = Color.BLACK;
        fontParameter.spaceX = 2;
        fontParameter.spaceY = 5;
        fontParameter.minFilter = Texture.TextureFilter.Nearest;
        fontParameter.magFilter = Texture.TextureFilter.Nearest;
        fontParameter.shadowOffsetX = 0;
        fontParameter.shadowOffsetY = 0;
        fontParameter.shadowColor = Color.BLACK;
        fontParameter.borderStraight = false;
        fontParameter.borderColor = Color.WHITE;
        fontParameter.gamma = 20f;

        return fontParameter;
    }

    public Button createSaveButton(FileHandle saveFile) {
        TextButton button = new TextButton(parseFilename(saveFile.name()), skin, "store-button");
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //dialog
                Dialog dialog = new Dialog("Load?", skin,"dialogStyle"){
                    //to quit when clicking yes
                    @Override
                    protected void result(Object object) {
                        if((boolean)object){
                            SaveLoad saveLoad = new SaveLoad(game,mainGame);
                            saveLoad.loadGame(SaveManager.localSavesRoot + "/" + saveFile.name());
                        }
                    }
                };

                dialog.button("Yes", true);
                dialog.button("No", false);
                Label.LabelStyle dialogLabelStyle = new Label.LabelStyle();
                dialogLabelStyle.font = skin.getFont("PressStart2P-big");


                //dialogLabelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textTooltipBackground.png"))));
                dialog.text("Are you sure you\n want to load this file?", dialogLabelStyle);
                dialog.show(mainStage);

            }
        });
        return button;
    }


    public static String parseFilename(String filename) {

        if (filename.endsWith(".data")) {
            filename = filename.substring(0, filename.length() - 5);
        }

        String[] parts = filename.split("--");

        // Capitalize each part of the name
        String[] nameParts = parts[0].split(" ");
        StringBuilder fullName = new StringBuilder();
        for (String name : nameParts) {
            if (!name.isEmpty()) {
                fullName.append(Character.toUpperCase(name.charAt(0)))
                    .append(name.substring(1)).append(" ");
            }
        }

        // Parse date-time
        String[] dateTimeParts = parts[1].split("_");

        String day = dateTimeParts[0];
        int monthNumber = Integer.parseInt(dateTimeParts[1]);
        String year = dateTimeParts[2];

        String[] timeParts = dateTimeParts[3].split("-");
        String hour = timeParts[0];
        String minute = timeParts[1];

        String monthName = new java.text.DateFormatSymbols().getMonths()[monthNumber - 1];

        return fullName.toString().trim() + " " + day + " " + monthName + " " + year + " " + hour + ":" + minute;
    }

    public Table createSavesTable() {
        Table saveFilesTable = new Table(skin);
        FileHandle saveDir = Gdx.files.local("saves");
        if (saveDir.exists()) {
            FileHandle[] saveFiles = saveDir.list(".data");
            for (FileHandle saveFile : saveFiles) {
                saveFilesTable.add(createSaveButton(saveFile)).pad(30).width(1700).height(200);
                saveFilesTable.row();
            }
        }
        return saveFilesTable;
    }
}


