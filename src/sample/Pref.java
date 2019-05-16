package sample;

import javafx.stage.Stage;

//This abstract class is responsible for holding the current stage that is presented
//It is used to close the window of the application from the Controller class whenever
//Exit button is clicked

public abstract class Pref {

    private static Stage currPref;

    public void setCurrPref(Stage stage)
    {
        currPref = stage;
    }

    public Stage getCurrPref() {
        return currPref;
    }
}
