package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.bilcitytycoon.BilCityTycoonGame;
import io.github.bilcitytycoon.Building;
import io.github.bilcitytycoon.Faculty;
import io.github.bilcitytycoon.Main;

public class StoreScreen implements Screen {
    private FitViewport fitViewport;
    private Stage stage;
    private Skin skin;
    private BilCityTycoonGame game;
    private Main mainGame;

    public StoreScreen(BilCityTycoonGame game, Main mainGame){
        this.game = game;
        this.mainGame = mainGame;
        this.stage = new Stage();
        this.fitViewport = new FitViewport(1366,768);
        stage.setViewport(fitViewport);

        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(36,1);

        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(20,1);

        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(12,0);


        BitmapFont bigFont = bigFontGenerator.generateFont(bigFontParameter);
        BitmapFont smallFont = bigFontGenerator.generateFont(smallFontParameter);
        BitmapFont smallestFont = bigFontGenerator.generateFont(smallestFontParameter);

        skin = new Skin();
        skin.add("PressStart2P", bigFont);
        skin.add("PressStart2P-small", smallFont);
        skin.add("PressStart2P-smallest", smallestFont);
        skin.addRegions(new TextureAtlas(Gdx.files.internal("skin1.atlas")));
        skin.load(Gdx.files.internal("skin1.json"));

        //TODO: temp
        Table buttonTable = new Table();
        buttonTable.setFillParent(true);
        buttonTable.center();

        //test
        Faculty test = new Faculty("Math",100,100,100,"libgdx.png","test test test test test test test",10);

        buttonTable.add(createFacultyButton(test));

        stage.addActor(buttonTable);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        fitViewport.update(width,height,true);
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
        stage.dispose();

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
        photoTable.add(new Image(faculty.getImage().getDrawable())).center().width(40).height(40);

        Table textTable = new Table(skin);

        //mid panel

        Label facultyLabel = new Label(faculty.getName(), skin,"default");
        textTable.add(facultyLabel).width(200).height(50);
        textTable.row();
        Label infoLabel = new Label(faculty.getInfo(),skin,"small-label");
        infoLabel.setWrap(true);
        textTable.add(infoLabel).width(200).height(50);
        textTable.row();

        //right info panel
        Table infoTable = new Table(skin);
        infoTable.defaults().pad(10);

        Table timeTable = new Table(skin);
        timeTable.defaults().pad(10);
        timeTable.add(new Image(new Texture(Gdx.files.internal("timeIcon.png")))).width(40).height(40);
        Label timeLabel = new Label(faculty.getBuildTime()+" days",skin,"small-label");
        timeTable.add(timeLabel);

        Table priceTable = new Table(skin);
        priceTable.defaults().pad(10);
        priceTable.add(new Image(new Texture(Gdx.files.internal("bilcoin.png")))).width(40).height(40);
        Label priceLabel = new Label(Double.toString(faculty.getCost()),skin,"small-label");
        priceTable.add(priceLabel);


        infoTable.add(timeTable);
        infoTable.add(priceTable);
        infoTable.row();

        //temp
        Label advantageLabel = new Label("+100 University Reputation Points\n+25 Student Satisfaction Point",skin,"smallest-label");
        infoTable.add(advantageLabel).colspan(2).center();



        mainTable.top();
        mainTable.add(photoTable).expand().fill().align(Align.left);
        mainTable.add(textTable).expand().fill();
        mainTable.add(infoTable).expand().fill().align(Align.right);


        mainTable.setFillParent(true);
        mainTable.center();
        mainTable.defaults().pad(40);

        button.add(mainTable).expand().fill().align(Align.left).top();
        return button;
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


