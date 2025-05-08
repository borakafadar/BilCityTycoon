package io.github.bilcitytycoon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import io.github.bilcitytycoon.Map;

/**
 * Manages the in-game tutorial overlay using libGDX’s Scene2D UI.
 * Walks the player through each core gameplay step with avatar pop-ups
 * and map/UI highlights.
 **/

public class Tutorial {
    private final int WELCOME = 0;
    private final int BUILD_RECTORATE = 1;
    private final int BUILD_FACULTY = 2;
    private final int HANDLE_BUDGET = 3;
    private final int CHECK_SATISFACTION = 4;
    private final int END_TUTORIAL = 5;

    /*private final Stage stage;
    private final Skin skin;
    private final Map map;*/

    private Table popupTable;
    private Image avatarImage;
    private Label messageLabel;
    private Texture[] avatars;
    private TextButton nextButton;

    private int currentStep;

   /**
     * Creates and starts the tutorial sequence.
     *
     //* @param stage the Scene2D Stage on which to display tutorial UI
     //* @param skin  the UI Skin (json + atlas) for styling widgets
     //* @param map   the game Map, used for highlighting and resuming play
     */
/*
    public Tutorial(Stage stage, Skin skin, Map map)
    {
        this.stage = stage;
        this.skin = skin;
        this.map = map;
        loadAvatars();
        buildUi();
        startTutorial();
    }*/

   //Loads all avatar textures used in the tutorial pop-ups.
    private void loadAvatars()
    {
        avatars = new Texture[5];
        avatars[0]= new Texture(""); //Yaşar these pictures should be added into icons
        avatars[1]= new Texture(""); // Bora
        avatars[2]= new Texture(""); //Vural
        avatars[3]= new Texture(""); // Eylül
        avatars[4]= new Texture(""); // Zeynel

    }

    //Builds the pop-up Table, avatar Image, message Label, and Next button,then adds them to the stage.
    /*private void buildUi()
    {
        popupTable = new Table(skin);
        popupTable.setFillParent(true);
        popupTable.pad(20).background("window");

        avatarImage = new Image(avatars[0]);
        messageLabel = new Label("", skin);
        messageLabel.setWrap(true);
        nextButton = new TextButton("Next", skin);
        nextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                nextStep();
            }
        });

        // Layout: avatar on left, text on right, button below
        popupTable.add(avatarImage).size(120).padRight(15);
        popupTable.add(messageLabel).expandX().fillX();
        popupTable.row().padTop(10);
        popupTable.add(nextButton).colspan(2).center();
        stage.addActor(popupTable);
    }*/
    //Initializes the tutorial at the first step.
    /*public void startTutorial()
    {
        currentStep = WELCOME;
        showStep();
    }

    // continues to the next tutorial step, or finishes if on the last step.
    public void nextStep() {
        currentStep++;
        showStep();
    }*/

    /**
     * Displays the UI and map/UI highlights for the current step.
     * Clears any previous highlights before applying the new ones.
     */
   /* public void showStep()
    {
        //Clear previous highlights
        //map.clearHighlights();

        switch(currentStep){
            case WELCOME:
                avatarImage.setDrawable(new TextureRegionDrawable(new TextureRegion(avatars[0])));
                messageLabel.setText("Welcome to BilCity Tycoon! In this tutorial, you will learn how to build your campus while managing your budget, and keeping your students happy.");
                break;

            case BUILD_RECTORATE:
                avatarImage.setDrawable(new TextureRegionDrawable(
                    new TextureRegion(avatars[1])));
                messageLabel.setText(
                    "First, let’s place your Rectorate. Click on a yellow-highlighted grid to begin construction.");
                map.highlightGrids("buildable");  // highlights all buildable grid cells
                break;

            case BUILD_FACULTY:
                avatarImage.setDrawable(new TextureRegionDrawable(
                    new TextureRegion(avatars[2])));
                messageLabel.setText(
                    "Now build a Faculty. Make sure you have enough BilCoins and an available grid."
                );
                map.highlightGrids("faculty-zone");
                break;

            case HANDLE_BUDGET:
                avatarImage.setDrawable(new TextureRegionDrawable(
                    new TextureRegion(avatars[3])));
                messageLabel.setText(
                    "Keep an eye on your Budget panel! Income comes from students and events; expenses from construction and maintenance."
                );
                map.highlightUI("budgetPanel");
                break;

            case CHECK_SATISFACTION:
               avatarImage.setDrawable(new TextureRegionDrawable(
                    new TextureRegion(avatars[4])));
                messageLabel.setText(
                    "Student Satisfaction affects your reputation. Happy students bring more BilCoins and higher rankings."
                );
                map.highlightUI("satisfactionMeter");
                break;

            case END_TUTORIAL:
                finishTutorial();
                return;
        }
    }*/

     // Removes the tutorial overlay and resumes normal gameplay.
    /*private void finishTutorial() {
        popupTable.remove();
        map.clearHighlights();
        // Signal the game that tutorial is over, e.g.:
        map.getGame().resumePlay();
    }*/

  // Checks whether the tutorial is still active.
    public boolean isActive() {
        return currentStep <= END_TUTORIAL;
    }
}
