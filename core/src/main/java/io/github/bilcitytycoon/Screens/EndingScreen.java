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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.bilcitytycoon.BilCityTycoonGame;
import io.github.bilcitytycoon.Main;

/**
 * A reusable ending screen class that can show messages and two action buttons.
 * Useful for displaying end states like game over, congratulations, or summary screens.
 */
public class EndingScreen implements Screen {

    /** Reference to the main game instance */
    private BilCityTycoonGame game;

    /** Stage for drawing UI */
    private Stage stage;

    /** UI skin containing fonts and styles */
    private Skin skin;

    /** Viewport for fitting the stage */
    private FitViewport fitViewport;

    /** Reference to the main app controller for screen transitions */
    private Main main;

    /** Reference to the screen that invoked this one (optional) */
    private Screen previousScreen;

    /**
     * Basic constructor: shows a message with two buttons that redirect to either the previous screen or welcome screen.
     *
     * @param game The game logic
     * @param main The screen transition controller
     * @param title Title of the screen
     * @param info Description or message to display
     * @param leftButtonText Text for the left button
     * @param rightButtonText Text for the right button
     * @param previousScreen The screen to return to on left button press
     */
    public EndingScreen(BilCityTycoonGame game, Main main, String title, String info, String leftButtonText, String rightButtonText, Screen previousScreen) {
        this.game = game;
        this.skin = createSkin();
        this.main = main;

        stage = new Stage();
        fitViewport = new FitViewport(1280, 720);
        stage.setViewport(fitViewport);

        Table rootTable = new Table();
        rootTable.setFillParent(true);

        Table panelTable = new Table();
        panelTable.setBackground(new TextureRegionDrawable(new Texture(Gdx.files.internal("panelBackground.png"))));
        panelTable.pad(50);
        panelTable.defaults().pad(30);

        Label titleLabel = new Label(title, skin);
        panelTable.add(titleLabel).colspan(2).center();
        panelTable.row();

        Label infoLabel = new Label(info, skin);
        infoLabel.setWrap(true);
        panelTable.add(infoLabel).colspan(2).width(700).center();
        panelTable.row();

        TextButton leftButton = new TextButton(leftButtonText, skin);
        TextButton rightButton = new TextButton(rightButtonText, skin);

        panelTable.add(leftButton).width(300).height(100);
        panelTable.add(rightButton).width(300).height(100);

        rootTable.add(panelTable).center();
        stage.addActor(rootTable);

        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(previousScreen);
            }
        });

        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new WelcomeScreen(main));
            }
        });
    }

    /**
     * Alternative constructor: opens UniversityStatsPopup instead of changing screen.
     *
     * @param game The game logic
     * @param main The screen transition controller
     * @param title Title of the screen
     * @param info Description or message to display
     * @param leftButtonText Text for the left button
     * @param previousScreen The screen to pass into LoadGameScreen
     * @param rightButtonText Text for the right button
     */
    public EndingScreen(BilCityTycoonGame game, Main main, String title, String info, String leftButtonText, Screen previousScreen, String rightButtonText) {
        this.game = game;
        this.skin = createSkin();
        this.main = main;

        stage = new Stage();
        fitViewport = new FitViewport(1280, 720);
        stage.setViewport(fitViewport);

        Table rootTable = new Table();
        rootTable.setFillParent(true);

        Table panelTable = new Table();
        panelTable.setBackground(new TextureRegionDrawable(new Texture(Gdx.files.internal("panelBackground.png"))));
        panelTable.pad(50);
        panelTable.defaults().pad(30);

        Label titleLabel = new Label(title, skin);
        panelTable.add(titleLabel).colspan(2).center();
        panelTable.row();

        Label infoLabel = new Label(info, skin);
        infoLabel.setWrap(true);
        panelTable.add(infoLabel).colspan(2).width(700).center();
        panelTable.row();

        TextButton leftButton = new TextButton(leftButtonText, skin);
        TextButton rightButton = new TextButton(rightButtonText, skin);

        panelTable.add(leftButton).width(300).height(100);
        panelTable.add(rightButton).width(300).height(100);

        rootTable.add(panelTable).center();
        stage.addActor(rootTable);

        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new LoadGameScreen(main, previousScreen));
            }
        });

        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                UniversityStatsPopup.show(stage, skin, game.getPlayer());
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width, height, true);
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

    public Skin createSkin() {
        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(72,1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16,1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(13,0);
        FreeTypeFontGenerator.FreeTypeFontParameter defaultFontParameter = generateFontParameter(36,1);

        BitmapFont bigFont = bigFontGenerator.generateFont(bigFontParameter);
        BitmapFont smallFont = bigFontGenerator.generateFont(smallFontParameter);
        BitmapFont smallestFont = bigFontGenerator.generateFont(smallestFontParameter);
        BitmapFont defaultFont = bigFontGenerator.generateFont(defaultFontParameter);

        Skin skin1 = new Skin();
        skin1.add("PressStart2P", defaultFont);
        skin1.add("PressStart2P-small", smallFont);
        skin1.add("PressStart2P-smallest", smallestFont);
        skin1.add("PressStart2P-big", bigFont);
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
}
