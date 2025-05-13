package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tutorial {
    // Tutorial steps
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

    private final Stage stage;
    private final Skin skin;
    private final Map map;
    private final BilCityTycoonGame game;

    private Window tutorialWindow;
    private Image avatarImage;
    private Label messageLabel;
    private TextButton nextButton;
    private TextButton skipButton;
    private Texture[] avatars;
    private int currentStep;

    // Tutorial content arrays
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

    private final String[] highlightAreas = {
        null,                  // Welcome
        "universityName",      // Name university
        "buildableArea",       // Rectorate
        "storeButton",        // Store
        "facultyTab",         // Faculty
        "bilcoinsPanel",      // Budget
        "otherBuildingsTab",  // Dorm
        "satisfactionMeter",  // Satisfaction
        "timeControls",       // Time
        null,                 // Tips
        null                  // End
    };

    public Tutorial(Stage stage, Skin skin, Map map, BilCityTycoonGame game) {
        this.stage = stage;
        this.skin = skin;
        this.map = map;
        this.game = game;
        this.currentStep = WELCOME;
        
        loadAvatars();
        createTutorialWindow();
    }

    private void loadAvatars() {
        avatars = new Texture[5];
        // Load character sprites for each team member
        avatars[0] = new Texture(Gdx.files.internal("assets/character sprites/Yasar_default.png"));
        avatars[1] = new Texture(Gdx.files.internal("assets/character sprites/Bora_default.png"));
        avatars[2] = new Texture(Gdx.files.internal("assets/character sprites/Vural_default.png"));
        avatars[3] = new Texture(Gdx.files.internal("assets/character sprites/Eylul_default.png"));
        avatars[4] = new Texture(Gdx.files.internal("assets/character sprites/Zeynel_default.png"));
    }

    private void createTutorialWindow() {
        tutorialWindow = new Window("Tutorial", skin);
        tutorialWindow.setMovable(false);
        
        // Create content table
        Table content = new Table(skin);
        content.pad(20);
        
        // Add avatar image
        avatarImage = new Image(avatars[0]);
        content.add(avatarImage).size(150).padRight(20);
        
        // Add message label
        messageLabel = new Label("", skin);
        messageLabel.setWrap(true);
        content.add(messageLabel).width(400).expandX().fillX();
        
        content.row().pad(20);
        
        // Add buttons
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
        
        // Center window
        tutorialWindow.setPosition(
            (stage.getWidth() - tutorialWindow.getWidth()) / 2,
            (stage.getHeight() - tutorialWindow.getHeight()) / 2
        );
    }

    public void startTutorial() {
        currentStep = WELCOME;
        stage.addActor(tutorialWindow);
        showCurrentStep();
    }

    // Change this method from private to public:
    public void nextStep() {
        // Increment current step
        currentStep++;
        
        // Check if we've reached the end
        if (currentStep > END_TUTORIAL) {
            // Reset to last step to avoid index errors
            currentStep = END_TUTORIAL;
            return;
        }
        
        // Clear previous highlights
        map.clearHighlights();
        
        // Add new highlights if needed
        String highlightArea = highlightAreas[currentStep];
        if (highlightArea != null) {
            map.highlightArea(highlightArea);
        }
        
        // Update UI elements
        if (messageLabel != null) {
            messageLabel.setText(getCurrentMessage());
        }
        
        // Update avatar if available
        if (avatarImage != null && avatars != null && avatars.length > 0) {
            avatarImage.setDrawable(new TextureRegionDrawable(
                new TextureRegion(avatars[currentStep % avatars.length]))
            );
        }
        
        // Update button text for last step
        if (nextButton != null && currentStep == END_TUTORIAL) {
            nextButton.setText("Start Playing!");
        }
        
        System.out.println("Tutorial advanced to step " + currentStep + ": " + getCurrentMessage());
    }

    private void showCurrentStep() {
        // Clear previous highlights
        map.clearHighlights();
        
        // Update avatar (cycle through team members)
        avatarImage.setDrawable(new TextureRegionDrawable(
            new TextureRegion(avatars[currentStep % avatars.length]))
        );
        
        // Update message
        messageLabel.setText(messages[currentStep]);
        
        // Add highlights if needed
        String highlightArea = highlightAreas[currentStep];
        if (highlightArea != null) {
            map.highlightArea(highlightArea);
        }
        
        // Update next button text for last step
        if (currentStep == END_TUTORIAL) {
            nextButton.setText("Start Playing!");
        }
    }

    private void endTutorial() {
        // Clean up
        tutorialWindow.remove();
        map.clearHighlights();
        
        // Dispose textures
        for (Texture texture : avatars) {
            texture.dispose();
        }
        
        // Signal game that tutorial is complete
        game.setTutorialComplete(true);
    }

    public boolean isActive() {
        return currentStep <= END_TUTORIAL;
    }

    public void dispose() {
        for (Texture texture : avatars) {
            if (texture != null) {
                texture.dispose();
            }
        }
    }


    public void skipTutorial() {
        map.clearHighlights();


        if (tutorialWindow != null) {
            tutorialWindow.remove();
        }
        
        // Skip to end
        currentStep = END_TUTORIAL;
        
        // Signal game that tutorial is complete
        game.setTutorialComplete(true);
    }


    public int getCurrentStep() {
        return currentStep;
    }

    public String getCurrentMessage() {
        if (currentStep >= 0 && currentStep < messages.length) {
            return messages[currentStep];
        }
        return "Tutorial complete!";
    }

 
    public boolean isComplete() {
        return currentStep >= END_TUTORIAL;
    }

// Add these helper methods for the Map class:
private void updateHighlights() {
    // Clear existing highlights
    map.clearHighlights();
    
    // Add new highlight if needed
    String highlightArea = highlightAreas[currentStep];
    if (highlightArea != null) {
        map.highlightArea(highlightArea);
    }
}

private void updateUI() {
    // Update avatar
    if (avatarImage != null) {
        avatarImage.setDrawable(new TextureRegionDrawable(
            new TextureRegion(avatars[currentStep % avatars.length]))
        );
    }   
    
    // Update message
    if (messageLabel != null) {
        messageLabel.setText(getCurrentMessage());
    }
    
    // Update next button text for last step
    if (nextButton != null && currentStep == END_TUTORIAL - 1) {
        nextButton.setText("Start Playing!");
    }
    }
}