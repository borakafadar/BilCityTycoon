package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Handles the tutorial logic in BilCity Tycoon, guiding new players through step-by-step UI interactions
 * such as naming the university, placing buildings, and understanding game mechanics.
 */
public class Tutorial {

    // Tutorial step constants
    public static final int WELCOME = 0;
    public static final int NAME_UNIVERSITY = 1;
    public static final int BUILD_RECTORATE = 2;
    public static final int OPEN_STORE = 3;
    public static final int BUILD_FACULTY = 4;
    public static final int CHECK_BUDGET = 5;
    public static final int BUILD_DORM = 6;
    public static final int CHECK_SATISFACTION = 7;
    public static final int TIME_CONTROLS = 8;
    public static final int FINAL_TIPS = 9;
    public static final int END_TUTORIAL = 10;

    /** The main UI stage where tutorial elements are shown */
    private final Stage stage;

    /** Skin used to style UI elements */
    private final Skin skin;

    /** Reference to the map (used for highlighting areas, if needed) */
    private final Map map;

    /** Reference to the main game instance */
    private final BilCityTycoonGame game;

    /** The main tutorial popup window */
    private Window tutorialWindow;

    /** Avatar image displayed with each message */
    private Image avatarImage;

    /** Label that displays the current tutorial message */
    private Label messageLabel;

    /** Button to go to the next tutorial step */
    private TextButton nextButton;

    /** Button to skip the tutorial */
    private TextButton skipButton;

    /** Array of avatar textures used for visual guidance */
    private Texture[] avatars;

    /** Current step index of the tutorial */
    private int currentStep;

    /** Instructional messages for each tutorial step */
    private final String[] messages = {
        "Welcome to BilCity Tycoon! I'm Yaşar, and I'll guide you through building your dream university.",
        "First, let's name your university. Click on the text field above and enter a name that reflects your vision.",
        "Great! Now let's build your Rectorate - the heart of your university. Look for the highlighted yellow area on the map.",
        "Click the Store button at the top of the screen. This is where you'll find all buildings and upgrades.",
        "Time to build your first faculty! Choose one from the Faculties tab. Remember to check the cost and reputation points.",
        "Keep an eye on your BilCoins! Click the coin icon to see your income and expenses breakdown.",
        "Students need a place to stay. Build a dormitory from the Other Buildings section.",
        "Student satisfaction is crucial! Watch the satisfaction meter and add facilities to keep it high.",
        "Use the time controls to speed up or pause the game. Each semester takes 4 months.",
        "Remember: Happy students = More BilCoins = Better reputation. Balance your expenses and keep expanding!",
        "You're ready to build your university empire! Good luck!"
    };

    /** UI component IDs to highlight (currently unused) */
    private final String[] highlightAreas = {
        null,
        "universityName",
        "buildableArea",
        "storeButton",
        "facultyTab",
        "bilcoinsPanel",
        "otherBuildingsTab",
        "satisfactionMeter",
        "timeControls",
        null,
        null
    };

    /**
     * Constructs the tutorial and prepares the avatars and window.
     */
    public Tutorial(Stage stage, Skin skin, Map map, BilCityTycoonGame game) {
        this.stage = stage;
        this.skin = skin;
        this.map = map;
        this.game = game;
        this.currentStep = WELCOME;

        loadAvatars();
        createTutorialWindow();
    }

    /**
     * Loads the character avatars used in the tutorial.
     */
    private void loadAvatars() {
        avatars = new Texture[5];
        avatars[0] = new Texture(Gdx.files.internal("assets/character sprites/Yasar_default.png"));
        avatars[1] = new Texture(Gdx.files.internal("assets/character sprites/Bora_default.png"));
        avatars[2] = new Texture(Gdx.files.internal("assets/character sprites/Vural_default.png"));
        avatars[3] = new Texture(Gdx.files.internal("assets/character sprites/Eylul_default.png"));
        avatars[4] = new Texture(Gdx.files.internal("assets/character sprites/Zeynel_default.png"));
    }

    /**
     * Creates and configures the tutorial window UI.
     */
    private void createTutorialWindow() {
        tutorialWindow = new Window("Tutorial", skin);
        tutorialWindow.setMovable(false);

        Table content = new Table(skin);
        content.pad(20);

        avatarImage = new Image(avatars[0]);
        content.add(avatarImage).size(150).padRight(20);

        messageLabel = new Label("", skin);
        messageLabel.setWrap(true);
        content.add(messageLabel).width(400).expandX().fillX();

        content.row().pad(20);

        Table buttonTable = new Table(skin);

        skipButton = new TextButton("Skip Tutorial", skin);
        skipButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                endTutorial();
            }
        });

        nextButton = new TextButton("Next", skin);
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                nextStep();
            }
        });

        buttonTable.add(skipButton).padRight(20);
        buttonTable.add(nextButton);

        content.add(buttonTable).colspan(2).right();
        tutorialWindow.add(content).expand().fill();
        tutorialWindow.pack();

        tutorialWindow.setPosition(
            (stage.getWidth() - tutorialWindow.getWidth()) / 2,
            (stage.getHeight() - tutorialWindow.getHeight()) / 2
        );
    }

    /**
     * Starts the tutorial by showing the first message.
     */
    public void startTutorial() {
        currentStep = WELCOME;
        stage.addActor(tutorialWindow);
        showCurrentStep();
    }

    /**
     * Moves to the next step of the tutorial.
     */
    public void nextStep() {
        currentStep++;

        if (currentStep > END_TUTORIAL) {
            currentStep = END_TUTORIAL;
            return;
        }

        String highlightArea = highlightAreas[currentStep];
        if (highlightArea != null) {
            //map.highlightArea(highlightArea);
        }

        if (messageLabel != null) {
            messageLabel.setText(getCurrentMessage());
        }

        if (avatarImage != null && avatars != null && avatars.length > 0) {
            avatarImage.setDrawable(new TextureRegionDrawable(
                new TextureRegion(avatars[currentStep % avatars.length]))
            );
        }

        if (nextButton != null && currentStep == END_TUTORIAL) {
            nextButton.setText("Start Playing!");
        }

        System.out.println("Tutorial advanced to step " + currentStep + ": " + getCurrentMessage());
    }

    /**
     * Displays the current message and avatar image.
     */
    private void showCurrentStep() {
        avatarImage.setDrawable(new TextureRegionDrawable(
            new TextureRegion(avatars[currentStep % avatars.length]))
        );

        messageLabel.setText(messages[currentStep]);

        String highlightArea = highlightAreas[currentStep];
        if (highlightArea != null) {
            //map.highlightArea(highlightArea);
        }

        if (currentStep == END_TUTORIAL) {
            nextButton.setText("Start Playing!");
        }
    }

    /**
     * Ends the tutorial, removes UI, and disposes resources.
     */
    private void endTutorial() {
        tutorialWindow.remove();
        for (Texture texture : avatars) {
            texture.dispose();
        }
        //game.setTutorialComplete(true);
    }

    /**
     * @return whether the tutorial is still active.
     */
    public boolean isActive() {
        return currentStep <= END_TUTORIAL;
    }

    /**
     * Disposes all avatar textures.
     */
    public void dispose() {
        for (Texture texture : avatars) {
            if (texture != null) {
                texture.dispose();
            }
        }
    }

    /**
     * Skips the tutorial and jumps to the last step.
     */
    public void skipTutorial() {
        if (tutorialWindow != null) {
            tutorialWindow.remove();
        }

        currentStep = END_TUTORIAL;

        //game.setTutorialComplete(true);
    }

    /**
     * @return the current tutorial step index.
     */
    public int getCurrentStep() {
        return currentStep;
    }

    /**
     * @return the message corresponding to the current step.
     */
    public String getCurrentMessage() {
        if (currentStep >= 0 && currentStep < messages.length) {
            return messages[currentStep];
        }
        return "Tutorial complete!";
    }

    /**
     * @return true if the tutorial has reached its final step.
     */
    public boolean isComplete() {
        return currentStep >= END_TUTORIAL;
    }

    /**
     * Updates the highlighted area on the map (not currently functional).
     */
    private void updateHighlights() {
        String highlightArea = highlightAreas[currentStep];
        if (highlightArea != null) {
            //map.highlightArea(highlightArea);
        }
    }

    /**
     * Updates the avatar and button text based on the current step.
     */
    private void updateUI() {
        if (avatarImage != null) {
            avatarImage.setDrawable(new TextureRegionDrawable(
                new TextureRegion(avatars[currentStep % avatars.length]))
            );
        }

        if (messageLabel != null) {
            messageLabel.setText(getCurrentMessage());
        }

        if (nextButton != null && currentStep == END_TUTORIAL - 1) {
            nextButton.setText("Start Playing!");
        }
    }
}
