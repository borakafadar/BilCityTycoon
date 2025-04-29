package io.github.bilcitytycoon;

public class Tutorial {
    private final int WELCOME = 0;
    private final int BUILD_RECTORATE = 1;
    private final int BUILD_FACULTY = 2;
    private final int HANDLE_BUDGET = 3;
    private final int CHECK_SATISFACTION = 4;
    private final int END_TUTORIAL = 5;

    private boolean isActive;
    private Map map;
    private int currentStep;


    public Tutorial(Map map)
    {
        currentStep = 0;
        this.map = map;
        this.isActive = true;
        startTutorial();
    }

    public void startTutorial()
    {
    }

    public void nextStep()
    {
        switch(currentStep){
            case WELCOME:
                System.out.println("Welcome to Bilcity Tycoon game");
                System.out.println("This is a game that unravel your creativity");
                //TODO
                break;
            case BUILD_FACULTY:
                System.out.println("To build a faculty building you should have adequate amount of bilcoins");
                System.out.println("Also there should be available grids to put a faculty building");
                //TODO
                break;
            case HANDLE_BUDGET:
                System.out.println("Handling budget is essential aspect of the game that player should consider");
                System.out.println("There are income sources and some outcomes");
                //TODO
                break;
            case CHECK_SATISFACTION:
                System.out.println("Satisfaction is ");
                //TODO
                break;
            case END_TUTORIAL:
                //TODO
                break;
            default:
                break;
        }
    }

    private void displayMessage(String message)
    {
        //map.displayPopUp(message); // might be changed
    }

    private void highlightBuilding(String buildingType)
    {
        //TODO
    }

    public boolean isTutorialActive()
    {
        return isActive;
    }

}
