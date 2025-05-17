package io.github.bilcitytycoon.Screens.Store;

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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.bilcitytycoon.BilCityTycoonGame;
import io.github.bilcitytycoon.Main;
import io.github.bilcitytycoon.Screens.GameScreen;

/**
 * This screen acts as the main hub for the store, allowing the player
 * to navigate to subcategories such as Faculties, Other Buildings,
 * Upgrades, and Decorations. It also provides a close button to return
 * to the game screen.
 */
public class StoreScreen implements Screen {

    /** Viewport used for fitting UI elements to screen size */
    private FitViewport fitViewport;

    /** Viewport used to stretch the background across screen */
    private StretchViewport stretchViewport; //for the background

    /** Stage used to render UI elements */
    private Stage mainStage;

    /** Stage used to render the background panel */
    private Stage backgroundStage;

    /** Skin used for font and UI styling */
    private Skin skin;

    /** Reference to the main game instance */
    private BilCityTycoonGame game;

    /** Main application controller, used for screen transitions */
    private Main mainGame;

    /** Reference to this store screen for passing into sub-screens */
    private StoreScreen thisStoreScreen;

    /** Reference to the game screen to return to upon closing */
    private GameScreen gameScreen;

    /**
     * Constructs the StoreScreen with references to game logic, main app controller,
     * and game screen. Initializes fonts, layout, and store category buttons.
     *
     * @param game the main game logic controller
     * @param mainGame the screen manager for transitions
     * @param gameScreen the screen to return to from the store
     */
    public StoreScreen(BilCityTycoonGame game, Main mainGame, GameScreen gameScreen){
        //TODO the buttons in the stores are not finished because the store things are not finished

        this.game = game;
        this.mainGame = mainGame;
        this.mainStage = new Stage();
        this.fitViewport = new FitViewport(1920,1080);
        this.stretchViewport = new StretchViewport(1366,768);
        this.backgroundStage = new Stage();
        this.thisStoreScreen = this;
        this.gameScreen = gameScreen;
        mainStage.setViewport(fitViewport);
        backgroundStage.setViewport(stretchViewport);

        Image panelBackground = new Image(new Texture(Gdx.files.internal("panelBackground.png")));
        backgroundStage.addActor(panelBackground);
        panelBackground.setSize(1920,1080);

        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter  titleFontParameter = generateFontParameter(75,2);
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(34,1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16,1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(13,0);

        //TODO: please clean this code up, it works but it is really garbage

        BitmapFont bigFont = bigFontGenerator.generateFont(bigFontParameter);
        BitmapFont smallFont = bigFontGenerator.generateFont(smallFontParameter);
        BitmapFont smallestFont = bigFontGenerator.generateFont(smallestFontParameter);
        BitmapFont titleFont = bigFontGenerator.generateFont(titleFontParameter);

        skin = new Skin();
        skin.add("PressStart2P", bigFont);
        skin.add("PressStart2P-small", smallFont);
        skin.add("PressStart2P-smallest", smallestFont);
        skin.add("PressStart2P-big", titleFont);
        skin.addRegions(new TextureAtlas(Gdx.files.internal("skin1.atlas")));
        skin.load(Gdx.files.internal("skin1.json"));

        Label storeLabel = new Label("Store",skin,"title-label");

        ImageButton closeButton = new ImageButton(skin,"close-button");
        mainStage.addActor(closeButton);

        closeButton.setPosition(100,940);
        closeButton.getImage().setScale(2);

        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(gameScreen);
                dispose();
            }
        });

        Table rootTable = new Table();
        rootTable.setFillParent(true);

        rootTable.add(storeLabel).expandX().align(Align.center).padTop(70);
        rootTable.row();

        Table buttonTable = createStoresTable();
        rootTable.add(buttonTable).expand().padTop(100).align(Align.center);

        mainStage.addActor(rootTable);
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
        fitViewport.update(width,height,true);
        stretchViewport.update(width,height,true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        skin.dispose();
        mainStage.dispose();
    }

    /**
     * Creates a table containing buttons for each store category (Faculties, Other Buildings, etc.).
     * Sets their sizes and click listeners to navigate to the appropriate screens.
     *
     * @return A Table with all category buttons
     */
    private Table createStoresTable () {
        Table mainTable = new Table();

        final float BUTTON_WIDTH = 403*1.5f;
        final float BUTTON_HEIGHT = 229*1.5f;

        mainTable.top();
        mainTable.defaults().pad(30).uniform().minSize(BUTTON_WIDTH,BUTTON_HEIGHT);

        ImageButton facultyButton = new ImageButton(skin,"store-faculties-button");
        ImageButton otherBuildingsButton = new ImageButton(skin,"store-other-button");
        ImageButton upgradesButton = new ImageButton(skin,"store-upgrades-button");
        ImageButton decorationsButton = new ImageButton(skin,"store-decorations-button");

        facultyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(new FacultiesStoreScreen(game,mainGame,thisStoreScreen,gameScreen));
            }
        });

        otherBuildingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(new OtherBuildingsStoreScreen(game,mainGame,thisStoreScreen,gameScreen));
            }
        });

        upgradesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(new UpgradesStoreScreen(game,mainGame,thisStoreScreen));
            }
        });

        decorationsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(new DecorationsStoreScreen(game,mainGame,thisStoreScreen,gameScreen));
            }
        });

        //libGDX is garbage, and it will not change its button's image size, so you have to force it with this
        for (ImageButton btn : new ImageButton[]{facultyButton, otherBuildingsButton, upgradesButton, decorationsButton}) {
            btn.getImageCell().expand().fill();
        }

        mainTable.add(facultyButton);
        mainTable.add(otherBuildingsButton);
        mainTable.row();
        mainTable.add(upgradesButton);
        mainTable.add(decorationsButton);

        mainTable.center();
        mainTable.pack();

        return mainTable;
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
