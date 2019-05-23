package sample.java;

import javafx.application.Application;
import javafx.stage.Stage;

//This Main class is responsible for Viewing the content of the Stage
//It can be interpreted as the View class

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        //Setting the views for stage using ViewPrefs "Model" class
        //This form of implementation is to modularize each component of the application
        //as much as possible. Look up MVC/MVP/MVVM architectures.

        //ViewPrefs is a child of (extends) Pref ViewModel class
        //We set the current View (Stage) that is being displayed to that class
        //so that we can later close the window (Stage) from the Controller class
        //whenever the Exit button is clicked.
        Pref.setCurrPref(new ViewPrefs().getStage());

        Pref.getCurrPref().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
