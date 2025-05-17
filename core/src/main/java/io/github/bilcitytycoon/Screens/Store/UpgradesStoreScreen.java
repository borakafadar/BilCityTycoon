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
import io.github.bilcitytycoon.*;

import java.util.ArrayList;

/**
 * This screen displays all available upgrades for built faculties.
 * Each upgrade can be purchased and applied to improve performance or unlock features.
 */
public class UpgradesStoreScreen implements Screen {

    /** Viewport for scaling the main UI */
    private FitViewport fitViewport;

    /** Viewport for stretching the background */
    private StretchViewport stretchViewport; //for background

    /** Stage for rendering background visuals */
    private Stage backgroundStage;

    /** Stage for rendering interactive UI components */
    private Stage mainStage;

    /** Skin used for fonts and widget styles */
    private Skin skin;

    /** Reference to the game logic */
    private BilCityTycoonGame game;

    /** Reference to the screen manager */
    private Main mainGame;

    /** Reference to the previous store screen */
    private StoreScreen storeScreen;

    /** Store object containing available upgrades */
    private Store store;

    /**
     * Constructs the upgrade store screen, loads UI components and available upgrades.
     *
     * @param game The game logic controller
     * @param mainGame The screen transition controller
     * @param storeScreen The screen to return to
     */
    public UpgradesStoreScreen(BilCityTycoonGame game, Main mainGame, StoreScreen storeScreen){
        this.game = game;
        this.mainGame = mainGame;
        this.store = game.store;
        this.mainStage = new Stage();
        this.fitViewport = new FitViewport(1920,1080);
        this.storeScreen = storeScreen;
        this.stretchViewport = new StretchViewport(1366,768);
        this.backgroundStage = new Stage();

        backgroundStage.setViewport(stretchViewport);
        mainStage.setViewport(fitViewport);

        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter titleFontParameter = generateFontParameter(75,2);
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(34,1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16,0);
        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(13,1);

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

        //TODO: temp, to test the table feature
        Table buttonTable = createButtonTable(initializeUpgrades());
        buttonTable.setFillParent(true);

        Image panelBackground = new Image(new Texture(Gdx.files.internal("panelBackground.png")));
        backgroundStage.addActor(panelBackground);
        panelBackground.setSize(1920,1080);

        ScrollPane scrollPane = new ScrollPane(buttonTable,skin);
        scrollPane.setHeight(100);
        scrollPane.setScrollingDisabled(true,false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setClamp(true);
        scrollPane.setOverscroll(false,true);

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        buttonTable.bottom();
        buttonTable.pack();

        Label titleLabel = new Label("Upgrades",skin,"title-label");
        titleLabel.setAlignment(Align.center);
        rootTable.add(titleLabel).expandX().fillX().padTop(90).padBottom(50);
        rootTable.row();
        rootTable.add(scrollPane).expand().fill();

        ImageButton backButton = new ImageButton(skin,"back-button");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(storeScreen);
            }
        });

        mainStage.addActor(rootTable);
        mainStage.addActor(backButton);
        backButton.setPosition(100,920);
        backButton.setSize(100,100);
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
        stretchViewport.update(width, height,true);
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
     * Creates a button representing a single upgrade.
     * Clicking the button applies the upgrade and disables it.
     *
     * @param upgrade Upgrade to be applied
     * @return Button representing the upgrade
     */
    private Button createUpgradeButton(Upgrade upgrade){
        Button button = new Button(skin,"store-button");
        Table mainTable = new Table();
        mainTable.pad(10);
        mainTable.padBottom(20);

        Table photoTable = new Table(skin);
        photoTable.add(new Image(upgrade.getImage().getDrawable())).size(150,120).padRight(100);

        Table textTable = new Table(skin);
        Label facultyLabel = new Label(upgrade.getName(), skin,"default");
        facultyLabel.setWrap(true);
        facultyLabel.setAlignment(Align.center);
        textTable.add(facultyLabel).width(facultyLabel.getText().length*35).height(50).padBottom(20);
        textTable.row();
        Label infoLabel = new Label(upgrade.getInfo(),skin,"small-label");
        infoLabel.setWrap(true);
        textTable.add(infoLabel).width(facultyLabel.getText().length*25+75).height(50);
        textTable.row();

        Table infoTable = new Table(skin);
        infoTable.defaults().pad(10);

        Table timeTable = new Table(skin);
        timeTable.defaults().pad(10);
        timeTable.add(new Image(new Texture(Gdx.files.internal("icons/timeIcon.png")))).width(40).height(40);
        Label timeLabel = new Label(upgrade.getBuildTime()+" days",skin,"small-label");
        timeTable.add(timeLabel);

        Table priceTable = new Table(skin);
        priceTable.defaults().pad(10);
        priceTable.add(new Image(new Texture(Gdx.files.internal("icons/bilcoin.png")))).width(40).height(40);
        Label priceLabel = new Label(Double.toString(upgrade.getUpgradeCost()),skin,"small-label");
        priceTable.add(priceLabel);

        infoTable.add(timeTable);
        infoTable.add(priceTable);
        infoTable.row();

        Label advantageLabel = new Label("+100 University Reputation Points\n+25 Student Satisfaction Point",skin,"smallest-label");
        infoTable.add(advantageLabel).colspan(2).center();

        mainTable.defaults().pad(20);
        mainTable.top();
        mainTable.add(photoTable).expand().fill().align(Align.left);
        mainTable.add(textTable).expand().fill().width(textTable.getWidth()).align(Align.center);
        mainTable.add(infoTable).expand().fill().align(Align.right);

        mainTable.setFillParent(true);
        mainTable.center();
        mainTable.defaults().pad(100);

        button.add(mainTable).expand().fill().align(Align.left).top();
        button.pack();

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                upgrade.applyUpgrade(game.getPlayer().getMoneyHandler(), game.getPlayer());
                button.setDisabled(true);
            }
        });

        return button;
    }

    /**
     * Creates a scrollable table of all upgrade buttons.
     *
     * @param upgrades List of upgrades to be shown
     * @return Table containing all upgrade buttons
     */
    private Table createButtonTable(ArrayList<Upgrade> upgrades){
        Table buttonTable = new Table();

        for(Upgrade upgrade : upgrades){
            buttonTable.add(createUpgradeButton(upgrade)).width(1700).height(200).pad(20);
            buttonTable.row();
        }
        return buttonTable;
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

    /**
     * Initializes upgrades from all built faculties that haven't been applied yet.
     *
     * @return List of available upgrades
     */
    private ArrayList<Upgrade> initializeUpgrades(){
        ArrayList<Upgrade> upgrades = new ArrayList<>();
        for(Faculty faculty : game.store.getBuiltFaculties()){
            for(Upgrade upgrade : faculty.getUpgrades()){
                if(!upgrade.isMade()){
                    upgrades.add(upgrade);
                }
            }
        }

        return upgrades;
    }
}
