package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.bilcitytycoon.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.bilcitytycoon.Event;
import io.github.bilcitytycoon.Save.SaveLoad;
import io.github.bilcitytycoon.Screens.Store.StoreScreen;

import java.util.HashSet;
import java.util.Random;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private Building selectedBuilding;
    Stack mapStack;
    private Image previewImage;
    private Viewport screenViewport;
    private Stage stage;
    public static Skin skin;
    private Main mainGame;
    private NewGameScreen newGameScreen;
    private SettingsScreen settingsScreen;
    private LoadGameScreen loadGameScreen;
    private BilCityTycoonGame bilCityTycoonGame;
    private ImageTextButton coinBtn;
    private Time time;
    private Label dateLabel;
    private Label dayLabel;
    private Label ssrLabel;
    private ImageTextButton leaderButon;
    private Array<Map> mapList = new Array<Map>();
    private int currentMapIndex = 0;
    private Map currentMap;
    private Decoration selectedDecoration;
    private boolean isPlacingDecoration = false;
    private TextButton newGameButton;
    private TextButton loadGameButton;
    private TextButton settingsButton;
    private TextButton quitButton;
    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 15;
    private boolean isPlacingBuilding = false;
    private int hoveredGridX, hoveredGridY;
    private ShapeRenderer shapeRenderer;
    private Group buildingGroup = new Group();
    private final int cellSize = 64;
    private final int UI_TOP_BAR_HEIGHT = 75;
    private final int UI_BOTTOM_BAR_HEIGHT = 75;
    private float dayTimer = 0;
    private boolean isFast = false;
    float safeMargin = 100;
    private Stage hamburgerStage;
    private Table hmbrgrPanel;
    private ArrayList<Decoration> placedDecorations;

    private int lastPopupDay = 0;
    private int popupDayInterval = 14;
    private PopUpPanel currentPopup;

    public GameScreen(Main mainGame, BilCityTycoonGame game) {
        this.time = game.getTime();
        this.currentMap = game.getMap();
        this.placedDecorations = game.placedDecorations;

        bilCityTycoonGame = game;
        int balance = bilCityTycoonGame.getPlayer().getMoneyHandler().getBalance();
        hamburgerStage = new Stage();

        this.settingsScreen = new SettingsScreen(GameScreen.this, mainGame);

        this.mainGame = mainGame;
        screenViewport = new com.badlogic.gdx.utils.viewport.StretchViewport(1280, 720);

        stage = new Stage(screenViewport);


        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(36, 1);

        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16, 1);

        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(13, 0);

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
        hmbrgrPanel = new Table();
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
        settingsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(settingsScreen);
            }
        });
        TextButton saveBtn = new TextButton("Save Game", skin);
        saveBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SaveLoad save = new SaveLoad(bilCityTycoonGame, mainGame);
                save.saveGame();
            }
        });
        TextButton loadBtn = new TextButton("Load Game", skin);
        loadBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loadGameScreen = new LoadGameScreen(mainGame, GameScreen.this);
                mainGame.setScreen(loadGameScreen);
            }
        });
        TextButton exitBtn = new TextButton("Exit Game", skin);
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //dialog box
                Dialog dialog = new Dialog("Quit?", skin, "dialogStyle") {
                    //to quit when clicking yes
                    @Override
                    protected void result(Object object) {
                        if ((boolean) object) {
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

        topTable.add(hmbrgrBtn).pad(3).expandX().fillX().height(50).width(100).left();


        //making of store button
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle();
        style.up = skin.getDrawable("statusBarButtonUp");
        style.down = skin.getDrawable("statusBarButtonDown");
        style.over = skin.getDrawable("statusBarButtonHover");
        style.font = skin.getFont("PressStart2P-small");
        style.imageUp = new TextureRegionDrawable(new Texture(Gdx.files.internal("icons/storeIcon.png")));

        ImageTextButton storeBtn = new ImageTextButton("Store", style);
        storeBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(new StoreScreen(bilCityTycoonGame, mainGame, GameScreen.this));
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

        ImageTextButton nameBtn = new ImageTextButton(game.getPlayer().getName() + "\nBilCity University", nameStyle);
        nameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                UniversityStatsPopup.show(stage, skin, bilCityTycoonGame.getPlayer());
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

        ssrLabel = new Label("Student\nSatisfaction Rate %"+bilCityTycoonGame.getPlayer().studentSatisfactionRate, skin, "labelStyle");
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

        leaderButon = new ImageTextButton("#41", leaderStyle);
        leaderButon.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(new LeaderboardScreen(bilCityTycoonGame, mainGame, GameScreen.this));
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

        coinBtn = new ImageTextButton(balance + "\nBilcoins", coinStyle);
        coinBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //showEconomyPopup();
                EconomyPopUp.show(stage, skin, bilCityTycoonGame.getPlayer());
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
        dateLabel = new Label("   Fall\n 2024/2025", skin, "labelStyle");
        dateLabel.setFontScale(0.8f);
        datetable.add(dateIcon).size(50, 50).padRight(10);
        datetable.add(dateLabel);
        bottomTable.add(datetable).pad(3).width(200).height(50).left();

        //making of show of day

        Table dayTable = new Table();
        dayTable.align(Align.left);
        dayTable.setBackground(backgroundDrawable);

        Image dayIcon = new Image(new Texture(Gdx.files.internal("icons/sunIcon.png")));
        dayLabel = new Label("Day 33", skin, "labelStyle");
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

        ImageTextButton timerBtn = new ImageTextButton("Time\n 1X", timerStyle);
        timerBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!isFast) {
                    time.speedUpTime();
                    timerBtn.setText("Time\n 2X");
                } else {
                    time.resetTimeSpeed();
                    timerBtn.setText("Time\n 1X");
                }
                isFast = !isFast;
            }
        });
        timerBtn.getImageCell().height(35).width(35).padRight(10);
        bottomTable.add(timerBtn).height(50).width(180).expandX().right();


        mapStack = new Stack();
        mapStack.setFillParent(true);

        Image background2 = new Image(mainBack);
        mapStack.addActor(background2);

        Texture roadTexture = new Texture(Gdx.files.internal("road.png"));
        Image roadImage = new Image(roadTexture);


        roadImage.setSize(cellSize * 2, cellSize * 2);
        roadImage.setPosition(5 * cellSize, 5 * cellSize);
        mapStack.addActor(roadImage);

        mapStack.addActor(buildingGroup);
        buildingGroup.setTouchable(Touchable.disabled);

        Table midTable = new Table();
        midTable.add(mapStack).expand().fill();



        //merging all items
        rootTable.add(topTable).height(75).expandX().fillX();
        rootTable.row();
        rootTable.add(midTable).expand().fill();
        rootTable.row();
        rootTable.add(bottomTable).height(75).expandX().fillX();
        Texture leftArrow = new Texture(Gdx.files.internal("leftTriangle.png"));
        Texture rightArrow = new Texture(Gdx.files.internal("rightTriangle.png"));

        ImageButton leftArrowBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(leftArrow)));
        ImageButton rightArrowBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(rightArrow)));

        leftArrowBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentMapIndex > 0) {
                    currentMapIndex--;
                    currentMap = mapList.get(currentMapIndex);
                    refreshBuildings();
                    updateArrowButtonsVisibility(leftArrowBtn, rightArrowBtn);

                }
            }
        });

        rightArrowBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentMapIndex < mapList.size - 1) {
                    currentMapIndex++;
                    currentMap = mapList.get(currentMapIndex);
                    refreshBuildings();
                    updateArrowButtonsVisibility(leftArrowBtn, rightArrowBtn);

                }
            }
        });

        stage.addActor(leftArrowBtn);
        stage.addActor(rightArrowBtn);

        leftArrowBtn.setPosition(20, Gdx.graphics.getHeight() / 2f - 50);
        rightArrowBtn.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() / 2f - 50);
        updateArrowButtonsVisibility(leftArrowBtn, rightArrowBtn);
        stage.addActor(hmbrgrPanel);

    }

    @Override
    public void show() {

        refreshBuildings();
        shapeRenderer = new ShapeRenderer();
        stage.addActor(buildingGroup);

        //for show of building store items
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                if (isPlacingBuilding && selectedBuilding != null) {
                    Vector3 world = stage.getCamera().unproject(new Vector3(screenX, screenY, 0));
                    Actor hitActor = stage.hit(screenX, Gdx.graphics.getHeight() - screenY, true);

                    if (hitActor != null && !(hitActor instanceof Image) && !(hitActor.getParent() instanceof Group)) {
                        previewImage.setVisible(false);
                        return false;
                    }

                    hoveredGridX = (int) (world.x / cellSize);
                    hoveredGridY = (int) (world.y / cellSize);

                    int width = 2, height = 2;
                    boolean canPlace = canPlaceBuilding(hoveredGridX, hoveredGridY, width, height);

                    previewImage.setVisible(canPlace);
                    if (canPlace) {
                        previewImage.setPosition(hoveredGridX * cellSize, hoveredGridY * cellSize);
                    }
                } else {
                    previewImage.setVisible(false);
                }
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (isPlacingBuilding && selectedBuilding != null) {
                    Vector3 world = stage.getCamera().unproject(new Vector3(screenX, screenY, 0));
                    Actor hitActor = stage.hit(screenX, Gdx.graphics.getHeight() - screenY, true);

                    if (hitActor != null && !(hitActor instanceof Image) && !(hitActor.getParent() instanceof Group)) {
                        return false;
                    }

                    hoveredGridX = (int) (world.x / cellSize);
                    hoveredGridY = (int) (world.y / cellSize);

                    int w = 2, h = 2;
                    if (canPlaceBuilding(hoveredGridX, hoveredGridY, w, h)) {
                        placeBuilding(selectedBuilding, hoveredGridX, hoveredGridY, w, h);
                        if(selectedBuilding instanceof Faculty){
                            bilCityTycoonGame.store.getBuiltFaculties().add((Faculty) selectedBuilding);
                            bilCityTycoonGame.store.getUnbuiltFaculties().remove(selectedBuilding);
                        } else if(selectedBuilding instanceof OtherBuilding){
                            bilCityTycoonGame.store.getBuiltOtherBuildings().add((OtherBuilding) selectedBuilding);
                            bilCityTycoonGame.store.getUnbuiltOtherBuildings().remove(selectedBuilding);
                        }

                        selectedBuilding = null;
                        isPlacingBuilding = false;
                        previewImage.setVisible(false);
                    }
                }
                return false;
            }



        });
        Gdx.input.setInputProcessor(multiplexer);
        Texture previewTexture = new Texture(Gdx.files.internal("yellowImage.png"));
        previewImage = new Image(previewTexture);
        previewImage.setSize(cellSize * 3, cellSize * 2);
        previewImage.setVisible(false);
        stage.addActor(previewImage);

        hmbrgrPanel.toFront();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        screenViewport.apply();

        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.F1)) {
            bilCityTycoonGame.debugShowAllEndings = true;
        }

        if (bilCityTycoonGame.debugShowAllEndings) {
            showAllEndingsOnce();
            bilCityTycoonGame.debugShowAllEndings = false;
        }
        int currentDay = time.getTotalDaysPlayed();

        if (currentDay > 14 && currentDay >= lastPopupDay + popupDayInterval) {
            lastPopupDay = currentDay;
            showRandomPopup();
        }

        stage.act();
        stage.draw();
        if (isPlacingDecoration && selectedDecoration != null && selectedDecoration.getImage() != null) {
            Vector3 world = stage.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            previewImage.setVisible(true);
            previewImage.setPosition((int) (world.x / cellSize) * cellSize, (int) (world.y / cellSize) * cellSize);

            if (Gdx.input.justTouched()) {
                if (bilCityTycoonGame.getPlayer().getMoneyHandler().spend((int) selectedDecoration.getCost())) {
                    try {
                        Image placedImage = new Image(selectedDecoration.getImage().getDrawable());
                        placedImage.setSize(cellSize * 2, cellSize * 2);
                        placedImage.setPosition((int) (world.x / cellSize) * cellSize, (int) (world.y / cellSize) * cellSize);
                        buildingGroup.addActor(placedImage);

                        Decoration placedCopy = new Decoration(
                            selectedDecoration.getName(),
                            selectedDecoration.getCost(),
                            selectedDecoration.getImagePath(),
                            selectedDecoration.getInfo(),
                            0
                        );
                        placedCopy.setImage(placedImage);
                        placedCopy.setX(placedImage.getX());
                        placedCopy.setY(placedImage.getY());
                        placedDecorations.add(placedCopy);

                    } catch (Exception e) {
                        System.out.println("DECORATION PLACE ERROR: " + e.getMessage());
                        e.printStackTrace();
                    }

                    isPlacingDecoration = false;
                    selectedDecoration = null;
                    previewImage.setVisible(false);
                    bilCityTycoonGame.checkEnding(mainGame);
                }
            }

        }


        time.updateTime();
        updateDateLabels();

        dateLabel.setText(time.getSemesterName() + "\n " + time.getAcademicYear());
        dayLabel.setText("Day " + time.getTotalDaysPlayed());


        if (isPlacingBuilding && selectedBuilding  != null) {
            int width = 2;
            int height = 2;

            boolean canPlace = canPlaceBuilding(hoveredGridX, hoveredGridY, width, height);
            previewImage.setVisible(canPlace);

            if (canPlace) {
                previewImage.setPosition(hoveredGridX * cellSize, hoveredGridY * cellSize);
            }
        }
        if (time.totalDaysPlayed == dayTimer + 1) {
            dayTimer = 0;
            processDay();
        }
        dayTimer = time.totalDaysPlayed;

        coinBtn.getLabel().setText(bilCityTycoonGame.getPlayer().getMoneyHandler().getBalance() + "\nBilcoins");

        ssrLabel.setText("Student\nSatisfaction Rate %" + bilCityTycoonGame.getPlayer().studentSatisfactionRate);

        leaderButon.setText("#" + bilCityTycoonGame.getPlayer().getRanking());

    }

    @Override
    public void resize(int width, int height) {
        screenViewport.update(width, height, true);
        screenViewport.apply(true);
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
        stage.dispose();
        skin.dispose();
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

    private boolean canPlaceBuilding(int x, int y, int width, int height) {
        Building[][] grid = currentMap.getGrid();
        if (x + width > grid.length || y + height > grid[0].length) return false;

        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (grid[i][j] != null || currentMap.isRoad(i, j)) return false;
            }
        }
        return true;
    }



    private void placeBuilding(Building building, int x, int y, int width, int height) {
        if (!canPlaceBuilding(x, y, width, height)) return; // Ön kontrol yap!
        boolean didSpend = bilCityTycoonGame.getPlayer().getMoneyHandler().spend((int) building.getCost());
        if (!didSpend) return;
        bilCityTycoonGame.checkEnding(mainGame);


        currentMap.placeBuilding(building, x, y, width, height);
        if(building instanceof Faculty){
            bilCityTycoonGame.store.buyFaculty((Faculty) building,bilCityTycoonGame.getPlayer());
        } else if(building instanceof OtherBuilding){
            bilCityTycoonGame.store.buyOtherBuilding((OtherBuilding) building,bilCityTycoonGame.getPlayer());
        }

        bilCityTycoonGame.getLeaderboard().updateRanking();
        refreshBuildings();
    }







    public void startPlacing(Building building) {
        this.selectedBuilding = building;
        isPlacingBuilding = true;
    }


    public void processDay() {
        bilCityTycoonGame.getPlayer().getMoneyHandler().processDay();
        bilCityTycoonGame.getPlayer().safeDecreaseStudentSatisfactionPoint(100);
        bilCityTycoonGame.getLeaderboard().updateRanking();
        bilCityTycoonGame.checkEnding(mainGame);
    }



    private void showEconomyPopup() {
        Dialog dialog = new Dialog("Economy", skin, "dialogStyle");

        Label.LabelStyle boldStyle = new Label.LabelStyle(skin.getFont("PressStart2P-small"), Color.BLACK);

        Label incomeLabel = new Label("Incomes:\nCorporate: 5000\nDonations: 3000\nGrants: 5000", boldStyle);
        Label expenseLabel = new Label("Expenses:\nUtilities: 3000\nStaff: 2500", boldStyle);

        int totalIncome = bilCityTycoonGame.getPlayer().getMoneyHandler().getIncome();
        int totalExpense = bilCityTycoonGame.getPlayer().getMoneyHandler().getExpense();
        int netIncome = totalIncome - totalExpense;

        Label summaryLabel = new Label("Overall: " + (netIncome >= 0 ? "+" : "") + netIncome + " BilCoins", boldStyle);
        summaryLabel.setColor(netIncome >= 0 ? Color.GREEN : Color.RED);

        // Layout Table
        Table contentTable = new Table();
        contentTable.add(incomeLabel).pad(10).left();
        contentTable.add(expenseLabel).pad(10).left();
        contentTable.row();
        contentTable.add(summaryLabel).colspan(2).pad(20).center();

        dialog.getContentTable().add(contentTable).pad(20);
        dialog.button("Close");

        dialog.show(stage);
    }
    private void placeBuildingOnCurrentMap(Faculty faculty, int x, int y, int width, int height) {
        currentMap.placeBuilding(faculty, x, y, width, height);
        bilCityTycoonGame.getPlayer().getMoneyHandler().spend((int) faculty.getCost());
        bilCityTycoonGame.checkEnding(mainGame);
        refreshBuildings();
    }

    private void showRandomPopup() {
        if (currentPopup != null) {
            currentPopup.remove();
        }
        Random random = new Random();
        int randomType = random.nextInt(3);
        if (randomType == 0) {
            Event protestEvent = new Event("", 0, bilCityTycoonGame, -60);
            protestEvent.protest();
            currentPopup = new PopUpPanel(mainGame, bilCityTycoonGame, protestEvent, new TextButton("OK", skin));
            buildingGroup = new Group();
            stage.addActor(buildingGroup);

        } else if (randomType == 1) {
            if (!bilCityTycoonGame.getPlayer().getBuildings().isEmpty()) {
                Event fireEvent = new Event("", 0, bilCityTycoonGame, -100);
                Building targetBuilding = fireEvent.randomBuilding();
                fireEvent.burning(targetBuilding);
                currentPopup = new PopUpPanel(mainGame, bilCityTycoonGame, fireEvent, new TextButton("Yes", skin), new TextButton("No", skin));
            }
        } else {
            Event donationEvent = new Event("", 0, bilCityTycoonGame, 10);
            donationEvent.donation();
            currentPopup = new PopUpPanel(mainGame, bilCityTycoonGame, new TextButton("OK", skin), donationEvent);
        }

        if (currentPopup != null) {
            stage.addActor(currentPopup);
        }
    }

    private void refreshBuildings() {
        buildingGroup.clearChildren();

        Building[][] grid = currentMap.getGrid();
        HashSet<Building> added = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Building building = grid[i][j];
                if (building != null && !added.contains(building)) {
                    Image buildingImage = new Image(building.getImage().getDrawable());
                    float scale = 2f;
                    buildingImage.setSize(2 * cellSize * scale, 2 * cellSize * scale);
                    buildingImage.setPosition((i * cellSize) - (2 * cellSize * (scale - 1) / 2f), (j * cellSize) - (2 * cellSize * (scale - 1) / 2f));
                    buildingGroup.addActor(buildingImage);

                    added.add(building);
                }
            }
        }
        for (Decoration decoration : placedDecorations) {
            Image image = new Image(decoration.getImage().getDrawable());
            image.setSize(cellSize * 2, cellSize * 2);
            image.setPosition(decoration.getImage().getX(), decoration.getImage().getY());
            buildingGroup.addActor(image);
        }

    }



    private void updateDateLabels() {
        dateLabel.setText(time.getSemesterName() + "\n " + time.getAcademicYear());
        dayLabel.setText("Day " + time.getTotalDaysPlayed());
    }

    private void showAllEndingsOnce() {
        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                bilCityTycoonGame.presentUltimateEnding();
                mainGame.setScreen(new EndingScreen(bilCityTycoonGame, mainGame, bilCityTycoonGame.endingTitle, bilCityTycoonGame.endingInfo, bilCityTycoonGame.leftButtonText, bilCityTycoonGame.rightButtonText, mainGame.getScreen()));
            }
        }, 0);

        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                bilCityTycoonGame.presentTrueEnding();
                mainGame.setScreen(new EndingScreen(bilCityTycoonGame, mainGame, bilCityTycoonGame.endingTitle, bilCityTycoonGame.endingInfo, bilCityTycoonGame.leftButtonText, bilCityTycoonGame.rightButtonText, mainGame.getScreen()));
            }
        }, 3);

        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                bilCityTycoonGame.presentBankruptEnding();
                mainGame.setScreen(new EndingScreen(bilCityTycoonGame, mainGame, bilCityTycoonGame.endingTitle, bilCityTycoonGame.endingInfo, bilCityTycoonGame.leftButtonText, bilCityTycoonGame.rightButtonText, mainGame.getScreen()));
            }
        }, 6);

        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                bilCityTycoonGame.presentLawsuitEnding();
                mainGame.setScreen(new EndingScreen(bilCityTycoonGame, mainGame, bilCityTycoonGame.endingTitle, bilCityTycoonGame.endingInfo, bilCityTycoonGame.leftButtonText, bilCityTycoonGame.rightButtonText, mainGame.getScreen()));
            }
        }, 9);
    }

    private void updateArrowButtonsVisibility(ImageButton leftArrowBtn, ImageButton rightArrowBtn) {
        leftArrowBtn.setVisible(currentMapIndex > 0);
        rightArrowBtn.setVisible(currentMapIndex < mapList.size - 1);
    }


    public void startPlacing(Decoration decoration) {
        this.selectedDecoration = decoration;
        isPlacingDecoration = true;
    }


}
