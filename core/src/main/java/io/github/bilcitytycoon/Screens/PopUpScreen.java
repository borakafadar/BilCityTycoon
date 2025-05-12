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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.bilcitytycoon.BilCityTycoonGame;
import io.github.bilcitytycoon.Building;
import io.github.bilcitytycoon.Event;
import io.github.bilcitytycoon.Main;
import java.util.Random;

public class PopUpScreen implements Screen {
    private FitViewport fitViewport;
    private StretchViewport stretchViewport; //for background
    private Stage stage;
    private Skin skin;
    private Main main;
    private BilCityTycoonGame game;
    private Event event;
    private Texture characterTexture;

    public PopUpScreen(Main mainGame, Event event) {
        this.skin = createSkin();
        this.fitViewport = new FitViewport(1600, 200);
        this.stage = new Stage(fitViewport);
        this.event = event;
    }

    public Skin createSkin() {
        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(72, 1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16, 1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(13, 0);
        FreeTypeFontGenerator.FreeTypeFontParameter titleFontParameter = generateFontParameter(50, 2);

        BitmapFont bigFont = bigFontGenerator.generateFont(bigFontParameter);
        BitmapFont smallFont = bigFontGenerator.generateFont(smallFontParameter);
        BitmapFont smallestFont = bigFontGenerator.generateFont(smallestFontParameter);
        BitmapFont titleFont = bigFontGenerator.generateFont(titleFontParameter);

        Skin skin1 = new Skin();
        skin1.add("PressStart2P", bigFont);
        skin1.add("PressStart2P-small", smallFont);
        skin1.add("PressStart2P-smallest", smallestFont);
        skin1.add("PressStart2P-big", titleFont);

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

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        root.bottom().padBottom(50);
        stage.addActor(root);

        String avatar = event.getAvatar();
        characterTexture = new Texture(Gdx.files.internal(avatar));
        Image charImage = new Image(characterTexture);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = skin.getFont("PressStart2P-medium");
        style.fontColor = Color.BLACK;

        Label messageLabel = new Label(event.getInfo(), style);
        messageLabel.setWrap(true);

        int i = avatar.indexOf("_");
        String speakerName = avatar.substring(0, i);
        Label speakerLabel = new Label(speakerName, style);

        Table dialog = new Table(skin);
        dialog.setBackground("background.png");
        dialog.add(charImage).width(120).height(120).pad(10);
        dialog.add(messageLabel).width(450).padRight(20);
        dialog.row();
        dialog.add(speakerLabel).colspan(2).left().padLeft(10).padTop(5);
    }


    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stretchViewport.update(width, height, true);
        fitViewport.update(width, height, true);
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
}
