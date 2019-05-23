package sample.java;

import javafx.stage.Stage;

//This abstract class is responsible for holding the current stage that is presented
//It is used to close the window of the application from the Controller class whenever
//Exit button is clicked

public class Pref {

    private static Stage currPref;

    //For Controller testing
    private static Controller currController;

    public static void setCurrPref(Stage stage)
    {
        currPref = stage;
    }

    public static Stage getCurrPref() {
        return currPref;
    }

    //For Controller Testing
    public static void setCurrController(Controller currController)
    {
        Pref.currController = currController;
    }

    public static Controller getCurrController() {
        return currController;
    }
}
