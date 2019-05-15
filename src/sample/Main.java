package sample;

import javafx.application.Application;
import javafx.stage.Stage;

//This Main class is responsible for Viewing the content of the Stage
//It can be thought of as the View class

public class Main extends Application {

    private ViewPrefs viewPrefs;

    @Override
    public void start(Stage stage) throws Exception{

        //Setting the views for stage using ViewPrefs "Controller" class
        //This form of implementation is to modularize each component of the application
        //as much as possible. Look up MVC/MVP/MVVM architectures.
        viewPrefs = new ViewPrefs();

        viewPrefs.setCurrPref(viewPrefs.getStage());

        viewPrefs.getCurrPref().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
