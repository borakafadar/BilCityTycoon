package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.bilcitytycoon.Leaderboard;
import io.github.bilcitytycoon.Main;
import io.github.bilcitytycoon.Screens.Store.FacultiesStoreScreen;
import io.github.bilcitytycoon.Screens.Store.StoreScreen;

public class WelcomeScreen implements Screen {
    private ScreenViewport screenViewport;
    private Main main;
    private Stage stage;
    public static Skin skin;

    private TextButton newGameButton;
    private TextButton loadGameButton;
    private TextButton settingsButton;
    private TextButton quitButton;


    private SettingsScreen settingsScreen;
    private WelcomeScreen thisWelcomeScreen;
    private LeaderboardScreen leaderboardScreen;

    public WelcomeScreen(Main game){

        this.main = game;
        thisWelcomeScreen = this;

        screenViewport = new ScreenViewport();

        stage = new Stage(screenViewport);



        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(36,1);

        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16,1);

        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(13,0);




        BitmapFont bigFont = bigFontGenerator.generateFont(bigFontParameter);
        BitmapFont smallFont = bigFontGenerator.generateFont(smallFontParameter);
        BitmapFont smallestFont = bigFontGenerator.generateFont(smallestFontParameter);

        skin = new Skin();
        skin.add("PressStart2P", bigFont);
        skin.add("PressStart2P-small", smallFont);
        skin.add("PressStart2P-smallest", smallestFont);
        skin.add("PressStart2P-big", bigFont);

        skin.addRegions(new TextureAtlas(Gdx.files.internal("skin1.atlas")));



        skin.load(Gdx.files.internal("skin1.json"));

        settingsScreen = new SettingsScreen(thisWelcomeScreen,main);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bigFont;

        newGameButton = new TextButton("New Game", skin);
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new NewGameScreen(game,WelcomeScreen.this));
            }
        });

        loadGameButton = new TextButton("Load Game", skin);
        loadGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadGameScreen(game,new WelcomeScreen(game)));
            }
        });
        settingsButton = new TextButton("Settings", skin);
        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(settingsScreen);
            }
        });
        quitButton = new TextButton("Quit", skin,"exit-game-button");
        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //dialog box
                Dialog dialog = new Dialog("Quit?", skin,"dialogStyle"){
                    //to quit when clicking yes
                    @Override
                    protected void result(Object object) {
                        if((boolean)object){
                            Gdx.app.exit();
                        }
                    }
                };

                dialog.button("Yes", true);
                dialog.button("No", false);
                Label.LabelStyle dialogLabelStyle = new Label.LabelStyle();
                dialogLabelStyle.font = bigFont;


                //dialogLabelStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("textTooltipBackground.png")))); //todo: temp background
                dialog.text("Are you sure you\n want to quit?", dialogLabelStyle);
                dialog.show(stage);

            }
        });


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();
        mainTable.defaults().pad(10);

        Label welcomeLabel = new Label("BilCity Tycoon", skin);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(194 /255f ,250 / 255f,255 / 255f, 1f));
        pixmap.fill();
        Texture labelTexture = new Texture(pixmap);
        pixmap.dispose();

        welcomeLabel.getStyle().fontColor = Color.BLACK;

        welcomeLabel.getStyle().background = new TextureRegionDrawable(new TextureRegion(labelTexture));
        welcomeLabel.setAlignment(Align.center);
        mainTable.add(welcomeLabel).width(600).height(200).pad(20);
        mainTable.row();





        Table buttonTable = new Table();
        buttonTable.setFillParent(false);
        buttonTable.center();

        final int BUTTON_RATE=80;
        final int BUTTON_WIDTH = BUTTON_RATE*5;
        final int BUTTON_HEIGHT = BUTTON_RATE*2;

        buttonTable.add(newGameButton).width(BUTTON_WIDTH).height(BUTTON_HEIGHT);
        buttonTable.row();
        buttonTable.add(loadGameButton).width(BUTTON_WIDTH).height(BUTTON_HEIGHT);
        buttonTable.row();
        buttonTable.add(settingsButton).width(BUTTON_WIDTH).height(BUTTON_HEIGHT);
        buttonTable.row();
        buttonTable.add(quitButton).width(BUTTON_WIDTH).height(BUTTON_HEIGHT);

        mainTable.add(buttonTable).width(BUTTON_WIDTH).height(BUTTON_HEIGHT*4);
        mainTable.row();


        mainTable.background(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("bilkentDrone.jpg")))));
        stage.addActor(mainTable);


    }




    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        screenViewport.apply();
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        screenViewport.update(width,height,true);
        screenViewport.apply(true);
    }
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose(){
        stage.dispose();
        skin.dispose();
    }




    private FreeTypeFontGenerator.FreeTypeFontParameter generateFontParameter(int size, int borderWidth){
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
    SettingsScreen getSettingsScreen(){
        return settingsScreen;
    }
}
