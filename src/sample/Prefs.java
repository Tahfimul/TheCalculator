package sample;

import javafx.stage.Stage;

//This class contains all the methods we want implemented in the ViewPrefs class

public interface Prefs {

    void createParentView();

    void createTopContent();

    void createLeftContent();

    void createCenterContent();

    void createRightContent();

    void createScene();

    void createStage();

    Stage getStage();
}
