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
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.bilcitytycoon.BilCityTycoonGame;
import io.github.bilcitytycoon.Main;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.bilcitytycoon.Screens.Store.StoreScreen;

public class GameScreen implements Screen {
    private Viewport screenViewport;
    private Stage stage;
    public static Skin skin;
    private Main game;
    private NewGameScreen newGameScreen;
    private SettingsScreen settingsScreen;
    private LoadGameScreen loadGameScreen;
    private BilCityTycoonGame bilCityTycoonGame;

    private TextButton newGameButton;
    private TextButton loadGameButton;
    private TextButton settingsButton;
    private TextButton quitButton;

    public GameScreen(Main game,NewGameScreen newGameScreen) {

        bilCityTycoonGame = new BilCityTycoonGame();

        this.newGameScreen = newGameScreen;
        this.settingsScreen = new SettingsScreen(GameScreen.this, game);
        this.loadGameScreen = new LoadGameScreen(game, GameScreen.this);

        this.skin = WelcomeScreen.skin;
        this.game=game;
        screenViewport = new com.badlogic.gdx.utils.viewport.StretchViewport(1280, 720);

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


        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("PressStart2P-small");

        skin.add("labelStyle", labelStyle);
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.top();
        stage.addActor(rootTable);

        //These are for making map

        Texture mainBack = new Texture(Gdx.files.internal("Map Background.png"));
        Texture otherBack = new Texture(Gdx.files.internal("otherGround.png"));
        Image background = new Image(mainBack);
        Image otherBackground = new Image(otherBack);
        Table topTable = new Table();
        topTable.setBackground(new TextureRegionDrawable(new TextureRegion(otherBack)));


        //making of hamburger button

        Texture hmbrgrtexture = new Texture(Gdx.files.internal("icons/hamburgerIcon.png"));
        Drawable hmbrgrdrawable = new TextureRegionDrawable(new TextureRegion(hmbrgrtexture));
        Table hmbrgrPanel = new Table();
        hmbrgrPanel.setBackground(new TextureRegionDrawable(new Texture(Gdx.files.internal("panelBackground.png"))));
        hmbrgrPanel.setVisible(false);

        hmbrgrPanel.setSize(400, 500);
        hmbrgrPanel.setPosition(10, 215);

        ImageButton backButton = new ImageButton(skin, "back-button");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hmbrgrPanel.setVisible(false);
            }
        });
        TextButton settingsBtn = new TextButton("Settings", skin);
        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(settingsScreen);
            }
        });
        TextButton saveBtn = new TextButton("Save Game", skin);
        saveBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Save game with this button
            }
        });
        TextButton loadBtn = new TextButton("Load Game", skin);
        loadBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(loadGameScreen);
            }
        });
        TextButton exitBtn = new TextButton("Exit Game", skin);
        exitBtn.addListener(new ClickListener(){
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
                dialog.text("Are you sure you\n want to quit?", dialogLabelStyle);
                dialog.show(stage);
            }
        });

        hmbrgrPanel.defaults().pad(5).width(360).height(80);
        hmbrgrPanel.align(Align.left);
        hmbrgrPanel.add(backButton).padLeft(10).width(60).height(60).left().row();

        hmbrgrPanel.add(settingsBtn).row();
        hmbrgrPanel.add(saveBtn).row();
        hmbrgrPanel.add(loadBtn).row();
        hmbrgrPanel.add(exitBtn).row();

        ImageButton hmbrgrBtn = new ImageButton(hmbrgrdrawable);
        hmbrgrBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hmbrgrPanel.setVisible(!hmbrgrPanel.isVisible());
            }
        });
        stage.addActor(hmbrgrPanel);
        topTable.add(hmbrgrBtn).pad(3).expandX().fillX().height(50).width(100).left();


        //making of store button
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
        style.up = skin.getDrawable("statusBarButtonUp");
        style.down = skin.getDrawable("statusBarButtonDown");
        style.over = skin.getDrawable("statusBarButtonHover");
        style.font = skin.getFont("PressStart2P-small");
        style.imageUp = new TextureRegionDrawable(new Texture(Gdx.files.internal("icons/storeIcon.png")));

        ImageTextButton storeBtn = new ImageTextButton("Store", style);
        storeBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StoreScreen(bilCityTycoonGame,game,GameScreen.this));
            }
        });
        storeBtn.getLabel().setFontScale(0.7f);
        storeBtn.getImageCell().padRight(10);
        topTable.add(storeBtn).pad(3).expandX().fillX().height(50).width(130).left();

        //making of user's university name label and a button for stats

        ImageTextButton.ImageTextButtonStyle nameStyle = new ImageTextButton.ImageTextButtonStyle();
        nameStyle.up = skin.getDrawable("statusBarButtonUp");
        nameStyle.down = skin.getDrawable("statusBarButtonDown");
        nameStyle.over = skin.getDrawable("statusBarButtonHover");
        nameStyle.font = skin.getFont("PressStart2P-small");
        nameStyle.imageUp = new TextureRegionDrawable(new Texture(Gdx.files.internal("icons/bilkoamblemIcon.png")));

        ImageTextButton nameBtn = new ImageTextButton(newGameScreen.getPlayerName()+"\nBilCity University", nameStyle);
        nameBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //There will be a screen for user's stats
            }
        });
        nameBtn.getLabel().setFontScale(0.7f);
        nameBtn.getImageCell().height(37).width(37).padRight(10).left();
        nameBtn.getImageCell().right();
        topTable.add(nameBtn).height(50).width(330);

        //making of show of student satisfaction rate

        Drawable backgroundDrawable = skin.getDrawable("statusBarButtonUp");


        Table ssrTable = new Table();
        ssrTable.setBackground(backgroundDrawable);

        Label ssrLabel = new Label("Student\nSatisfaction Rate %80", skin, "labelStyle");
        ssrLabel.setFontScale(0.65f);
        ssrTable.add(ssrLabel);
        topTable.add(ssrTable).pad(3).expandX().fillX().height(50).width(300).center().right();



        //making of button of ranking universities

        ImageTextButton.ImageTextButtonStyle leaderStyle = new ImageTextButton.ImageTextButtonStyle();
        leaderStyle.up = skin.getDrawable("statusBarButtonUp");
        leaderStyle.down = skin.getDrawable("statusBarButtonDown");
        leaderStyle.over = skin.getDrawable("statusBarButtonHover");
        leaderStyle.font = skin.getFont("PressStart2P-small");
        leaderStyle.imageUp = new TextureRegionDrawable(new Texture(Gdx.files.internal("icons/rankingIcon.png")));

        ImageTextButton leaderButon = new ImageTextButton("#41", leaderStyle);
        leaderButon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LeaderboardScreen(bilCityTycoonGame,game,GameScreen.this));
            }
        });
        leaderButon.getLabel().setFontScale(0.7f);
        leaderButon.getImageCell().height(37).width(37).padRight(10).left();
        leaderButon.getImageCell().right();
        topTable.add(leaderButon).expandX().fillX().height(50).width(100);


        //making of show of bilcoin amount

        ImageTextButton.ImageTextButtonStyle coinStyle = new ImageTextButton.ImageTextButtonStyle();
        coinStyle.up = skin.getDrawable("statusBarButtonUp");
        coinStyle.down = skin.getDrawable("statusBarButtonDown");
        coinStyle.over = skin.getDrawable("statusBarButtonHover");
        coinStyle.font = skin.getFont("PressStart2P-small");
        coinStyle.imageUp = new TextureRegionDrawable(new Texture(Gdx.files.internal("icons/bilCoin.png")));

        ImageTextButton coinBtn = new ImageTextButton("9836\nBilcoins", coinStyle);
        coinBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
        });
        coinBtn.getLabel().setFontScale(0.7f);
        coinBtn.getImageCell().height(37).width(37).padRight(10).left();
        coinBtn.getImageCell().right();
        topTable.add(coinBtn).height(50).width(175);


        //making of show of date
        Table bottomTable = new Table();
        bottomTable.setBackground(new TextureRegionDrawable(new TextureRegion(otherBack)));
        bottomTable.align(Align.left);

        Table datetable = new Table();
        datetable.setBackground(backgroundDrawable);
        Image dateIcon = new Image(new Texture(Gdx.files.internal("icons/semesterIcon.png")));
        Label dateLabel = new Label("   Fall\n 2024/2025", skin, "labelStyle");
        dateLabel.setFontScale(0.8f);
        datetable.add(dateIcon).size(50, 50).padRight(10);
        datetable.add(dateLabel);
        bottomTable.add(datetable).pad(3).width(200).height(50).left();

        //making of show of day

        Table dayTable = new Table();
        dayTable.align(Align.left);
        dayTable.setBackground(backgroundDrawable); // bu satırı ekle

        Image dayIcon = new Image(new Texture(Gdx.files.internal("icons/sunIcon.png")));
        Label dayLabel = new Label("Day 33", skin, "labelStyle");
        dayLabel.setFontScale(0.8f);
        dayTable.add(dayIcon).size(40, 40).padRight(10);
        dayTable.add(dayLabel);
        bottomTable.add(dayTable).pad(3).height(50).left();

        //making of a button for speeding up time

        ImageTextButton.ImageTextButtonStyle timerStyle = new ImageTextButton.ImageTextButtonStyle();
        timerStyle.up = skin.getDrawable("statusBarButtonUp");
        timerStyle.down = skin.getDrawable("statusBarButtonDown");
        timerStyle.over = skin.getDrawable("statusBarButtonHover");
        timerStyle.font = skin.getFont("PressStart2P-small");
        timerStyle.imageUp = new TextureRegionDrawable(new Texture(Gdx.files.internal("icons/timeIcon.png")));

        ImageTextButton timerBtn = new ImageTextButton("Time\n 2X", timerStyle);
        timerBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //There will be a function for speeding up
            }
        });
        timerBtn.getImageCell().height(35).width(35).padRight(10);
        bottomTable.add(timerBtn).height(50).width(180).expandX().right();


        Table midTable = new Table();
        midTable.add(background).expand().fill();

        //merging all items
        rootTable.add(topTable).height(75).expandX().fillX();
        rootTable.row();
        rootTable.add(midTable).expand().fill();
        rootTable.row();
        rootTable.add(bottomTable).height(75).expandX().fillX();

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
    public void dispose() {
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

}
