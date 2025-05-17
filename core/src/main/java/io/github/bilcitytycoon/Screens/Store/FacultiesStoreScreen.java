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
import io.github.bilcitytycoon.Faculty;
import io.github.bilcitytycoon.Main;
import io.github.bilcitytycoon.Screens.GameScreen;

import java.util.ArrayList;

/**
 * Screen displaying the list of unbuilt faculties available for purchase.
 * Players can browse and select a faculty to place it on the game map.
 */
public class FacultiesStoreScreen implements Screen {

    /** Viewport for fitting UI to screen size */
    private FitViewport fitViewport;

    /** Viewport for stretching the background */
    private StretchViewport stretchViewport; //for background

    /** Stage rendering the background image */
    private Stage backgroundStage;

    /** Stage rendering the main UI */
    private Stage mainStage;

    /** Skin for styling UI elements */
    private Skin skin;

    /** Reference to the main game object */
    private BilCityTycoonGame game;

    /** Main application controller to switch between screens */
    private Main mainGame;

    /** Previous screen for navigation */
    private StoreScreen storeScreen;

    /** Screen where faculty placement will occur */
    private GameScreen gameScreen;

    /**
     * Constructor for FacultiesStoreScreen. Initializes stages, viewports, fonts, UI layout, and back navigation.
     *
     * @param game Reference to the game logic
     * @param mainGame Reference to the main controller class
     * @param storeScreen Screen to return to when back button is pressed
     * @param gameScreen Screen to place selected faculties
     */
    public FacultiesStoreScreen(BilCityTycoonGame game, Main mainGame, StoreScreen storeScreen,GameScreen gameScreen){
        this.game = game;
        this.mainGame = mainGame;
        this.mainStage = new Stage();
        this.fitViewport = new FitViewport(1920,1080);
        this.storeScreen = storeScreen;
        this.stretchViewport = new StretchViewport(1366,768);
        this.backgroundStage = new Stage();
        this.gameScreen = gameScreen;

        backgroundStage.setViewport(stretchViewport);
        mainStage.setViewport(fitViewport);

        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter  titleFontParameter = generateFontParameter(75,2);
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
        Table buttonTable = createButtonTable(game.store.getUnbuiltFaculties());
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

        Label titleLabel = new Label("Faculties",skin,"title-label");
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
     * Creates a button for the given faculty object with styled layout.
     * Clicking the button switches to placement mode on the game screen.
     *
     * @param faculty The faculty to display
     * @return A fully styled and functional Button
     */
    private Button createFacultyButton(Faculty faculty){
        Button button = new Button(skin,"store-button");
        Table mainTable = new Table();
        mainTable.pad(10);
        mainTable.padBottom(20);

        Table photoTable = new Table(skin);
        photoTable.add(new Image(faculty.getImage().getDrawable())).size(150,120).padRight(100);

        Table textTable = new Table(skin);
        Label facultyLabel = new Label(faculty.getName(), skin,"default");
        facultyLabel.setWrap(true);
        facultyLabel.setAlignment(Align.center);
        textTable.add(facultyLabel).width(facultyLabel.getText().length*35).height(50).padBottom(20);
        textTable.row();
        Label infoLabel = new Label(faculty.getInfo(),skin,"small-label");
        infoLabel.setWrap(true);
        textTable.add(infoLabel).width(facultyLabel.getText().length*25+75).height(50);
        textTable.row();

        Table infoTable = new Table(skin);
        infoTable.defaults().pad(10);

        Table timeTable = new Table(skin);
        timeTable.defaults().pad(10);
        timeTable.add(new Image(new Texture(Gdx.files.internal("icons/timeIcon.png")))).width(40).height(40);
        Label timeLabel = new Label(faculty.getBuildTime()+" days",skin,"small-label");
        timeTable.add(timeLabel);

        Table priceTable = new Table(skin);
        priceTable.defaults().pad(10);
        priceTable.add(new Image(new Texture(Gdx.files.internal("icons/bilcoin.png")))).width(40).height(40);
        Label priceLabel = new Label(Double.toString(faculty.getCost()),skin,"small-label");
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
                mainGame.setScreen(gameScreen);
                gameScreen.startPlacing(faculty);
            }
        });

        return button;
    }

    /**
     * Creates a scrollable table layout from a list of faculties.
     *
     * @param faculties List of faculty buildings to display
     * @return Table containing buttons for each faculty
     */
    private Table createButtonTable(ArrayList<Faculty> faculties){
        Table buttonTable = new Table();
        for(Faculty faculty : faculties){
            buttonTable.add(createFacultyButton(faculty)).width(1700).height(200).pad(10);
            buttonTable.row();
        }
        return buttonTable;
    }

    /**
     * Generates font parameters with given size and border for custom fonts.
     *
     * @param size Font size
     * @param borderWidth Border thickness
     * @return A configured FreeTypeFontParameter
     */
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
