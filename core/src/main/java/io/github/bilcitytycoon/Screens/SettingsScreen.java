package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.bilcitytycoon.Main;

public class SettingsScreen implements Screen {
    private Screen previousScreen;
    private FitViewport fitViewport;
    private Slider audioSlider;
    private Slider musicSlider;
    private CheckBox fullscreenCheckBox;
    private Stage stage;
    private Skin skin;


    public SettingsScreen(Screen previousScreen, Main mainUI) {
        this.previousScreen = previousScreen;

        stage = new Stage();
        fitViewport = new FitViewport(1280,720);
        stage.setViewport(fitViewport);



        //temp
        skin = WelcomeScreen.skin;

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontParameter fontParameter = generateFontParameter(36,1);


        BitmapFont font = fontGenerator.generateFont(fontParameter);


        skin.add("PressStart2P", font);
        skin.addRegions(new TextureAtlas(Gdx.files.internal("skin1.atlas")));
        fontGenerator.dispose();


        skin.load(Gdx.files.internal("skin1.json"));


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();
        mainTable.defaults().pad(40);
        //TODO: change background
        mainTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("TextTooltipBackground.png")))));

        stage.addActor(mainTable);
        Label settingsLabel = new Label("Settings",skin);
        mainTable.add(settingsLabel).width(200).height(50).padRight(150).colspan(2);

        mainTable.row();


        //TODO: make the slider work
        Label audioSliderLabel = new Label("Audio",skin);
        mainTable.add(audioSliderLabel).width(200).height(50).padRight(150);
        audioSlider = new Slider(0,100,1,false,skin);
        mainTable.add(audioSlider).width(400).height(50);
        mainTable.row();


        //TODO: make the slider work
        Label musicSliderLabel = new Label("Music",skin);
        mainTable.add(musicSliderLabel).width(200).height(50).padRight(150);
        musicSlider = new Slider(0,100,1,false,skin);
        mainTable.add(musicSlider).width(400).height(50);
        mainTable.row();


        Label fullscreenLabel = new Label("Fullscreen",skin);
        mainTable.add(fullscreenLabel).width(200).height(50).padRight(150);
        fullscreenCheckBox = new CheckBox("",skin);
        TextButton backButton = new TextButton("Back",skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainUI.setScreen(previousScreen);
            }
        });
        stage.addActor(backButton);



        fullscreenCheckBox.setChecked(false);

        fullscreenCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(fullscreenCheckBox.isChecked()){
                    Gdx.app.getGraphics().setFullscreenMode(Gdx.graphics.getDisplayMode());
                }else {
                    Gdx.app.getGraphics().setWindowedMode(1280,720);
                }
            }
        });
        mainTable.add(fullscreenCheckBox).width(200).height(50);
        mainTable.row();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        fitViewport.apply();
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        fitViewport.update(width,height,true);
        fitViewport.apply(true);
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

    private FreeTypeFontParameter generateFontParameter(int size,int borderWidth){
        FreeTypeFontParameter fontParameter = new FreeTypeFontParameter();
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
