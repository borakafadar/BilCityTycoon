package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.bilcitytycoon.BilCityTycoonGame;
import io.github.bilcitytycoon.Building;
import io.github.bilcitytycoon.Event;
import io.github.bilcitytycoon.Main;
import java.util.Random;

public class PopUpPanel extends Table {
    private Image avatar;
    private Texture avatarTexture;
    private Texture backgroundTexture;
    private Skin skin;
    private Main main;
    private BilCityTycoonGame game;
    private Event specialEvent;
    private TextButton OK;
    private TextButton yes;
    private TextButton no;

    public PopUpPanel(Main main, BilCityTycoonGame game, Event protest, TextButton OK) {
        this.main = main;
        this.game = game;
        this.skin = createSkin();
        this.avatarTexture = new Texture(Gdx.files.internal(randomAvatar()));
        this.backgroundTexture = new Texture(Gdx.files.internal("panelBackground.png"));
        this.avatar = new Image(avatarTexture);
        this.specialEvent = protest;

        this.setBackground(new TextureRegionDrawable(this.backgroundTexture));
        this.setVisible(true);
        this.setSize(1600, 300);
        this.setPosition(960, 200, Align.center);
        this.add(avatar).width(100).height(100).pad(100).align(Align.topRight);

        specialEvent.protest();
        String text = specialEvent.getInfo();
        Label infoLabel = new Label(text, skin);
        infoLabel.setWrap(true);
        infoLabel.setAlignment(Align.center);

        OK = new TextButton("OK", skin);
        OK.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });
    }

    public PopUpPanel(Main main, BilCityTycoonGame game, Event fire, TextButton yes, TextButton no) {
        this.main = main;
        this.game = game;
        this.skin = createSkin();
        this.avatarTexture = new Texture(Gdx.files.internal(randomAvatar()));
        this.backgroundTexture = new Texture(Gdx.files.internal("panelBackground.png"));
        this.avatar = new Image(avatarTexture);
        this.specialEvent = fire;

        this.setBackground(new TextureRegionDrawable(this.backgroundTexture));
        this.setVisible(true);
        this.setSize(1600, 300);
        this.setPosition(960, 200, Align.center);

        this.add(avatar).width(100).height(100).pad(100).align(Align.topRight);

        specialEvent.burning(randomBuilding());
        String text = specialEvent.getInfo();
        Label infoLabel = new Label(text, skin);
        infoLabel.setWrap(true);
        infoLabel.setAlignment(Align.center);

        yes = new TextButton("Yes", skin);
        yes.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                setVisible(false);
            }
        });

        no = new TextButton("No", skin);
        no.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                setVisible(false);
                game.getPlayer().setStudentSatisfactionRate(game.getPlayer().getStudentSatisfactionRate() - 30);
            }
        });
    }

    public PopUpPanel(Main main, BilCityTycoonGame game, TextButton OK, Event donation) {
        this.main = main;
        this.game = game;
        this.skin = createSkin();
        this.avatarTexture = new Texture(Gdx.files.internal(randomAvatar()));
        this.backgroundTexture = new Texture(Gdx.files.internal("panelBackground.png"));
        this.avatar = new Image(avatarTexture);
        this.specialEvent = donation;

        this.setBackground(new TextureRegionDrawable(this.backgroundTexture));
        this.setVisible(true);
        this.setSize(1600, 300);
        this.setPosition(960, 200, Align.center);
        this.add(avatar).width(100).height(100).pad(100).align(Align.topRight);

        specialEvent.donation();
        String text = specialEvent.getInfo();
        Label infoLabel = new Label(text, skin);
        infoLabel.setWrap(true);
        infoLabel.setAlignment(Align.center);

        OK = new TextButton("OK", skin);
        OK.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });
    }

    public String randomAvatar(){
        String[] avatars = {
            "libgdx.png"
        };
        Random rand = new Random();
        int randomIndex = rand.nextInt(avatars.length);
        return avatars[randomIndex];
    }

    public Building randomBuilding(){
        Random rand = new Random();
        return game.getPlayer().getBuildings().get(rand.nextInt(game.getPlayer().getBuildings().size()));
    }

    public Skin createSkin(){
        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(72,1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16,1);
        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(13,0);



        BitmapFont bigFont = bigFontGenerator.generateFont(bigFontParameter);
        BitmapFont smallFont = bigFontGenerator.generateFont(smallFontParameter);
        BitmapFont smallestFont = bigFontGenerator.generateFont(smallestFontParameter);

        Skin skin1 = new Skin();
        skin1.add("PressStart2P", bigFont);
        skin1.add("PressStart2P-small", smallFont);
        skin1.add("PressStart2P-smallest", smallestFont);
        skin1.add("PressStart2P-big", bigFont);

        skin1.addRegions(new TextureAtlas(Gdx.files.internal("skin1.atlas")));

        skin1.load(Gdx.files.internal("skin1.json"));

        return skin1;
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
