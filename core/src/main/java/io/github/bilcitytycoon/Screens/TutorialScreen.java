package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.bilcitytycoon.*;

public class TutorialScreen implements Screen {
    private final Main game;
    private final Stage stage;
    private final Skin skin;
    private final GameScreen gameScreen;
    private final Tutorial tutorial;
    private final BilCityTycoonGame gameInstance;

    private Window tutorialWindow;
    private Image avatarImage;
    private Label messageLabel;
    private TextButton nextButton;
    private TextButton skipButton;
    private Texture[] avatars;

    public TutorialScreen(Main game, BilCityTycoonGame gameInstance) {
        this.game = game;
        this.gameInstance = gameInstance;
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("assets/uiskin.json"));
        this.gameScreen = new GameScreen(game, gameInstance);
        this.tutorial = new Tutorial(gameInstance.getMap(), gameInstance);
        
        loadResources();
        createInitialChoice();
    }

    private void loadResources() {
        avatars = new Texture[5];
        avatars[0] = new Texture(Gdx.files.internal("assets/character sprites/Yasar_default.png"));
        avatars[1] = new Texture(Gdx.files.internal("assets/character sprites/Bora_default.png"));
        avatars[2] = new Texture(Gdx.files.internal("assets/character sprites/Vural_default.png"));
        avatars[3] = new Texture(Gdx.files.internal("assets/character sprites/Eylul_default.png"));
        avatars[4] = new Texture(Gdx.files.internal("assets/character sprites/Zeynel_default.png"));
    }

    private void createInitialChoice() {
        Dialog choice = new Dialog("Welcome to BilCity Tycoon!", skin) {
            @Override
            protected void result(Object object) {
                boolean startTutorial = (Boolean) object;
                if (startTutorial) {
                    remove();
                    startTutorialSequence();
                } else {
                    skipToGame();
                }
            }
        };

        choice.text("Would you like to learn how to build and manage your university?");
        choice.button("Skip Tutorial", false);
        choice.button("Start Tutorial", true);
        choice.setPosition(
            (Gdx.graphics.getWidth() - choice.getWidth()) / 2,
            (Gdx.graphics.getHeight() - choice.getHeight()) / 2
        );
        
        stage.addActor(choice);
    }

    private void startTutorialSequence() {
        // Create tutorial window
        tutorialWindow = new Window("Tutorial", skin);
        tutorialWindow.setMovable(false);
        
        Table content = new Table();
        content.pad(20);

        // Add avatar
        avatarImage = new Image(avatars[0]);
        content.add(avatarImage).size(150).padRight(20);

        // Add message
        messageLabel = new Label(tutorial.getCurrentMessage(), skin);
        messageLabel.setWrap(true);
        content.add(messageLabel).width(400).expandX().fillX();

        content.row().pad(20);

        // Add buttons
        Table buttonTable = new Table();
        
        skipButton = new TextButton("Skip Tutorial", skin);
        skipButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                skipToGame();
            }
        });

        nextButton = new TextButton("Next", skin);
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                nextTutorialStep();
            }
        });

        buttonTable.add(skipButton).padRight(20);
        buttonTable.add(nextButton);
        content.add(buttonTable).colspan(2).right();

        tutorialWindow.add(content).expand().fill();
        tutorialWindow.pack();
        tutorialWindow.setPosition(
            (Gdx.graphics.getWidth() - tutorialWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - tutorialWindow.getHeight()) / 2
        );

        stage.addActor(tutorialWindow);
    }

    private void nextTutorialStep() {
        tutorial.nextStep();
        
        if (tutorial.isComplete()) {
            skipToGame();
            return;
        }

        // Update avatar
        avatarImage.setDrawable(new TextureRegionDrawable(
            new TextureRegion(avatars[tutorial.getCurrentStep() % avatars.length]))
        );

        // Update message
        messageLabel.setText(tutorial.getCurrentMessage());

        // Update button text for last step
        if (tutorial.getCurrentStep() == Tutorial.END_TUTORIAL - 1) {
            nextButton.setText("Start Playing!");
        }
    }

    private void skipToGame() {
        tutorial.skipTutorial();
        game.setScreen(gameScreen);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        gameScreen.render(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        for (Texture avatar : avatars) {
            if (avatar != null) {
                avatar.dispose();
            }
        }
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
}