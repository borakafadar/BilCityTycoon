package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.bilcitytycoon.BilCityTycoonGame;
import io.github.bilcitytycoon.Main;
import io.github.bilcitytycoon.Player;
import io.github.bilcitytycoon.Save.SaveLoad;

public class NewGameScreen implements Screen {

    private FitViewport fitViewport;
    private StretchViewport stretchViewport; //for background
    private Stage backgroundStage;
    private Stage mainStage;
    private Skin skin;
    private Main mainGame;
    private String playerName;
    private WelcomeScreen thisWelcomeScreen;

    public NewGameScreen(Main mainGame,WelcomeScreen thisWelcomeScreen){
        this.skin = createSkin();
        this.mainStage = new Stage();
        this.fitViewport = new FitViewport(1920,1080);
        this.stretchViewport = new StretchViewport(1366,768);
        this.backgroundStage = new Stage();
        this.mainGame = mainGame;
        this.thisWelcomeScreen = thisWelcomeScreen;
        mainStage.setViewport(fitViewport);
        backgroundStage.setViewport(stretchViewport);
        Image panelBackground = new Image(new Texture(Gdx.files.internal("panelBackground.png")));
        panelBackground.setSize(1920,1080);
        backgroundStage.addActor(panelBackground);

        Table rootTable = new Table();
        rootTable.setFillParent(true);

        Label titleLabel = new Label("New Game", skin,"title-label");
        titleLabel.setAlignment(Align.center);

        rootTable.add(titleLabel).expandX().fillX().padTop(150).padBottom(50).top().size(400,100).align(Align.center).top();
        rootTable.row();


        TextField playerNameField = new TextField("",skin);
        playerNameField.setMessageText("Enter your name here");

        playerNameField.setMaxLength(18);
        playerNameField.setAlignment(Align.center);
        rootTable.add(playerNameField).expandX().fillX().pad(100).align(Align.center).height(300).padBottom(30);
        rootTable.row();
        TextButton startButton = new TextButton("Start Game",skin);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerName = playerNameField.getText().trim();
                System.out.println("Player name: " + playerName);
                BilCityTycoonGame newGame = new BilCityTycoonGame();
                newGame.getPlayer().setName(playerName);

                mainGame.setScreen(new GameScreen(mainGame,newGame));
            }
        });

        rootTable.add(startButton).width(startButton.getText().length()*100).pad(100).align(Align.center).height(300);
        mainStage.addActor(rootTable);

        ImageButton closeButton = new ImageButton(skin,"close-button");
        closeButton.setPosition(100,930);
        closeButton.getImage().setScale(2);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(new WelcomeScreen(mainGame));
            }
        });
        mainStage.addActor(closeButton);


    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(mainStage);
    }
    @Override
    public void render(float delta) {
        backgroundStage.act();
        stretchViewport.apply();
        backgroundStage.draw();


        mainStage.act();
        fitViewport.apply();
        mainStage.draw();
    }
    @Override
    public void resize(int width, int height) {
        stretchViewport.update(width,height,true);
        fitViewport.update(width, height,true);
    }
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {}


    public Skin createSkin(){
        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(72,1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16,1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(13,0);



        BitmapFont bigFont = bigFontGenerator.generateFont(bigFontParameter);
        BitmapFont smallFont = bigFontGenerator.generateFont(smallFontParameter);
        BitmapFont smallestFont = bigFontGenerator.generateFont(smallestFontParameter);

        Skin skin1 = new Skin();
        skin1.add("PressStart2P", bigFont);
        skin1.add("PressStart2P-small", smallFont);
        skin1.add("PressStart2P-smallest", smallestFont);
        skin1.add("PressStart2P-big", bigFont);

        skin1.addRegions(new TextureAtlas(Gdx.files.internal("skin1.atlas")));

        skin1.load(Gdx.files.internal("skin1.json"));

        return skin1;
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
    public String getPlayerName(){
        return playerName;
    }
    public WelcomeScreen getWelcomeScreen(){
        return thisWelcomeScreen;
    }
}
