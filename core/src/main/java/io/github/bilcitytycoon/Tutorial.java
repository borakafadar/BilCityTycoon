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
        //TODO
    }

    public void nextStep()
    {
        switch(currentStep){
            case WELCOME:
                //TODO
                break;
            case BUILD_FACULTY:
                //TODO
                break;
            case HANDLE_BUDGET:
                //TODO
                break;
            case CHECK_SATISFACTION:
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
        map.displayPopUp(message); // might be changed
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
