package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
        skin = createSkin();

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();
        mainTable.defaults().pad(40);

        mainTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("TextTooltipBackground.png")))));

        stage.addActor(mainTable);
        Label settingsLabel = new Label("Settings",skin);
        mainTable.add(settingsLabel).width(200).height(50).padRight(150).colspan(2);

        mainTable.row();


        Label audioSliderLabel = new Label("Audio",skin);
        mainTable.add(audioSliderLabel).width(200).height(50).padRight(150);
        audioSlider = new Slider(0,100,1,false,skin);
        mainTable.add(audioSlider).width(400).height(50);
        mainTable.row();


        Label musicSliderLabel = new Label("Music",skin);
        mainTable.add(musicSliderLabel).width(200).height(50).padRight(150);
        musicSlider = new Slider(0,1,0.01f,false,skin);
        mainTable.add(musicSlider).width(400).height(50);
        musicSlider.setValue(mainUI.music.getVolume());
        mainTable.row();
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainUI.music.setVolume(musicSlider.getValue());
            }
        });


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

    public Skin createSkin(){
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
