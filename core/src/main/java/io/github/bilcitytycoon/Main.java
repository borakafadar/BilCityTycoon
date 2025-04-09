package io.github.bilcitytycoon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Stage stage;
    private Skin skin;
    //TODO: meaningful button names
    private TextButton button1;
    private TextButton button2;
    private TextButton button3;
    private TextButton button4;


    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("bilkentDrone.jpg");

        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);


        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontParameter fontParameter = new FreeTypeFontParameter();
        fontParameter.size = 24;
        fontParameter.borderWidth = 2;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.color = Color.BLACK;
        BitmapFont font = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();

        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(194 /255f ,250 / 255f,255 / 255f, 1f));
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        labelStyle.background = new TextureRegionDrawable(new TextureRegion(texture));
        Label label = new Label("BilCity Tycoon", labelStyle);
        label.setSize(400,100);
        label.setPosition(450, 500);
        label.setAlignment(Align.center);
        label.getStyle().fontColor = Color.BLACK;
        stage.addActor(label);

        Texture upTexture = new Texture(Gdx.files.internal("button.png"));
        Texture downTexture = new Texture(Gdx.files.internal("button.png"));

        TextureRegionDrawable upDrawable = new TextureRegionDrawable(new TextureRegion(upTexture));
        TextureRegionDrawable downDrawable = new TextureRegionDrawable(new TextureRegion(downTexture));
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        style.up = upDrawable;
        style.down = downDrawable;
        style.font = font;
        button1 = new TextButton("New Game", style);
        button1.setSize(200, 60);
        button1.setPosition(
            Gdx.graphics.getWidth() / 2f - 100,
            Gdx.graphics.getHeight() / 2f + 10
        );
        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Butona tıklandı!");
            }
        });
        button2 = new TextButton("Load Game",style);
        button2.setSize(200, 60);
        button2.setPosition(
            Gdx.graphics.getWidth() / 2f - 100,
            Gdx.graphics.getHeight() / 2f - 70
        );

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Butona tıklandı!");
            }
        });
        button3 = new TextButton("Settings", style);
        button3.setSize(200, 60);
        button3.setPosition(
            Gdx.graphics.getWidth() / 2f - 100,
            Gdx.graphics.getHeight() / 2f - 150
        );

        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Butona tıklandı!");
            }
        });
        button4 = new TextButton("Exit Game", style);
        button4.setSize(200, 60);
        button4.setPosition(
            Gdx.graphics.getWidth() / 2f + 400,
            Gdx.graphics.getHeight() / 2f - 340
        );

        button4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Butona tıklandı!");
            }
        });

        stage.addActor(label);

        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);
        stage.addActor(button4);

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        batch.draw(image, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        stage.dispose();
        skin.dispose();
    }
}
