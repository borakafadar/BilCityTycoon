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

public class FacultiesStoreScreen implements Screen {
    private FitViewport fitViewport;
    private StretchViewport stretchViewport; //for background
    private Stage backgroundStage;
    private Stage mainStage;
    private Skin skin;
    private BilCityTycoonGame game;
    private Main mainGame;
    private StoreScreen storeScreen;
    private GameScreen gameScreen;

    public FacultiesStoreScreen(BilCityTycoonGame game, Main mainGame, StoreScreen storeScreen, GameScreen gameScreen) {
        this.game = game;
        this.mainGame = mainGame;
        this.mainStage = new Stage();
        this.gameScreen = gameScreen;
        this.fitViewport = new FitViewport(1920,1080);
        this.storeScreen = storeScreen;
        this.stretchViewport = new StretchViewport(1366,768);
        this.backgroundStage = new Stage();

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
        Table buttonTable = new Table();
        buttonTable.setFillParent(true);

        Image panelBackground = new Image(new Texture(Gdx.files.internal("panelBackground.png")));
        backgroundStage.addActor(panelBackground);
        panelBackground.setSize(1920,1080);

       //test
        Faculty test = new Faculty("Mathematics Building",100,100,100,"sprites/Mathematics Building.png", 10,"test test test test test test test");
        buttonTable.add(createFacultyButton(test)).width(1700).height(200).pad(10);
        buttonTable.row();

//        Faculty test1 = new Faculty("Mathematics Building", 100, 100, 100, "sprites/Mathematics Building.png", "A place for solving complex equations and theoretical puzzles.", 8);
//
//
//        Faculty test3 = new Faculty("Physics Faculty", 95, 130, 105, "sprites/Physics Building.png", "Exploring gravity, quantum particles, and everything in between.", 12);
//
//        Faculty test4 = new Faculty("Biology & Life Sciences", 110, 85, 90, "sprites/Biology Building.png", "Dive into the wonders of DNA, evolution, and living organisms.", 9);
//
//        Faculty test5 = new Faculty("Architecture Studio", 130, 110, 115, "sprites/Architecture Building.png", "Designing spaces with precision, creativity, and a lack of sleep.", 14);
//
//        Faculty test6 = new Faculty("Literature and Arts", 100, 75, 120, "sprites/Architecture Building.png", "A world of metaphors, stories, and endless interpretation.", 11);
//
//        Faculty test7 = new Faculty("Psychology Department", 105, 100, 100, "sprites/Psychology Building.png", "Understanding how the mind works, one experiment at a time.", 10);
//
//        Faculty test8 = new Faculty("Political Science", 90, 85, 95, "sprites/Political Science Building.png", "Analyzing power, governments, and global systems of influence.", 10);
//
//        Faculty test9 = new Faculty("Engineering Faculty", 140, 120, 110, "sprites/EEE Building.png", "Building bridges, machines, and extremely long equations.", 13);
//
//        Faculty test10 = new Faculty("Environmental Sciences", 100, 95, 105, "sprites/Industrial Engineering Building.png", "Studying ecosystems, climate change, and sustainable solutions.", 9);
//
       Button testButton = createFacultyButton(test);
//        Button testButton1 = createFacultyButton(test1);
//        Button testButton2 = createFacultyButton(test2);
//        Button testButton3 = createFacultyButton(test3);
//        Button testButton4 = createFacultyButton(test4);
//        Button testButton5 = createFacultyButton(test5);
//        Button testButton6 = createFacultyButton(test6);
//        Button testButton7 = createFacultyButton(test7);
//        Button testButton8 = createFacultyButton(test8);
//        Button testButton9 = createFacultyButton(test9);
//        Button testButton10 = createFacultyButton(test10);
//
        testButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.startPlacing(test);
                mainGame.setScreen(gameScreen);

            }
        });
//        testButton1.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test1);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//
//
//        testButton2.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test2);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//
//        testButton3.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test3);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//        testButton4.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test4);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//        testButton5.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test5);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//        testButton6.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test6);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//        testButton7.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test7);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//        testButton8.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test8);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//        testButton9.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test9);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//        testButton10.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                gameScreen.startPlacing(test10);
//                mainGame.setScreen(gameScreen);
//
//            }
//        });
//        buttonTable.add(testButton).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton1).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton2).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton3).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton4).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton5).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton6).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton7).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton8).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton9).width(1700).height(200).pad(10);
//        buttonTable.row();
//
//        buttonTable.add(testButton10).width(1700).height(200).pad(10);
//        buttonTable.row();


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


    private Button createFacultyButton(Faculty faculty){
        Button button = new Button(skin,"store-button");
        Table mainTable = new Table();
        mainTable.pad(10);
        mainTable.padBottom(20);
        //photo
        Table photoTable = new Table(skin);
        //instancing a new image because FOR SOME REASON
        //libGDX does not allow the usage of the same image in multiple buttons ???
        photoTable.add(new Image(faculty.getImage().getDrawable())).size(150,120).padRight(100);

        Table textTable = new Table(skin);

        //mid panel

        Label facultyLabel = new Label(faculty.getName(), skin,"default");
        facultyLabel.setWrap(true);
        facultyLabel.setAlignment(Align.center);
        textTable.add(facultyLabel).width(facultyLabel.getText().length*35).height(50).padBottom(20);
        textTable.row();
        Label infoLabel = new Label(faculty.getInfo(),skin,"small-label");
        infoLabel.setWrap(true);
        textTable.add(infoLabel).width(facultyLabel.getText().length*25+75).height(50);
        textTable.row();

        //right info panel
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

        //temp
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
        return button;
    }

    private Table createButtonTable(){
        //TODO
        Table buttonTable = new Table();

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
}


