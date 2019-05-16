package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//In this class we are essentially setting all the views(aka content) for the stage
//In other words we are setting the contents of the Stage.
//This class can be interpreted as a model class

public class ViewPrefs extends Pref implements Prefs {

    private HBox topContent;
    private VBox leftContent;
    private GridPane centerContent;
    private VBox rightContent;

    //This class is used to set clickListeners and other controls for buttons and output area
    private Controller controller;

    //For the parentView we are using a type of JavaFx "layout" known as BorderPane.
    //You may choose to Google it to understand its components.
    //Here is the gist of the layout
    //---------------------------------------------------------------------------------------
    //|                                                                                     |
    //|                                     TOP CONTENT                                     |
    //|                                                                                     |
    //|-------------------------------------------------------------------------------------|
    //|             LEFT          |          CENTER           |  RIGHT                      |
    //|            CONTENT        |          CONTENT          |  CONTENT                    |
    //|                           |                           |                             |
    //|                           |                           |                             |
    //|                           |                           |                             |
    //|                           |                           |                             |
    //|                           |                           |                             |
    //|                           |                           |                             |
    //|                           |                           |                             |
    //|                           |                           |                             |
    //|-------------------------------------------------------------------------------------|
    //|                                                                                     |
    //|                                     BOTTOM CONTENT                                  |
    //|                                                                                     |
    //---------------------------------------------------------------------------------------
    private BorderPane parentView;
    private Scene scene;
    private Stage stage;

    public ViewPrefs()
    {
        createParentView();
        createScene();
        createStage();
    }

    @Override
    public void createParentView()
    {
        createTopContent();

        //Here we initialize the Controller class object by passing the TextArea (outputArea) view (that was created when calling the
        // createTopContent method). In this TextArea view the results will be output.
        controller = new Controller((TextArea) topContent.getChildren().get(0));

        createLeftContent();
        createCenterContent();
        createRightContent();

        //Since our calculator has a gist layout of such for the parentView:
        //---------------------------------------------------------------------------------------
        //|                                         TOP CONTENT                                 |
        //|                                     TextArea (outputArea)                           |
        //|                                                                                     |
        //|-------------------------------------------------------------------------------------|
        //|          LEFT             ||          CENTER           ||          RIGHT            |
        //|          CONTENT          ||          CONTENT          ||          CONTENT          |
        //|          ___              ||   ___    ___    ___       ||          ___              |
        //|         | + |  - ADD      ||  | 0 |  | 1 |  | 2 |      ||         | C |  - CLEAR    |
        //|                           ||                           ||                           |
        //|         | - |  - SUBTRACT ||  | 3 |  | 4 |  | 5 |      ||         | M |  - MEMORY   |
        //|                           ||                           ||                           |
        //|         | x |  - MULTIPLY ||  | 6 |  | 7 |  | 8 |      ||         | E |  - EXIT     |
        //|                           ||                           ||                           |
        //|         | / |  - DIVIDE   ||  | 9 |                    ||         | R |  - RECALL   |
        //|                           ||                           ||                           |
        //|                           ||                           ||         |   |             |
        //|                           ||                           ||         | = |  - EQUALS   |
        //|                           ||                           ||         |   |             |
        //---------------------------------------------------------------------------------------
        //|                                       BOTTOM CONTENT                                |
        //|           In our case we don't have any bottom content, so it will be empty.        |
        //---------------------------------------------------------------------------------------

        parentView = new BorderPane();
        parentView.setTop(topContent);
        parentView.setRight(rightContent);
        parentView.setLeft(leftContent);
        parentView.setCenter(centerContent);
    }

    //Since our calculator has a gist layout of such for the top content:
    //---------------------------------------------------------------------------------------
    //|                                                                                     |
    //|                                     TextArea (outputArea)                           |
    //|                                                                                     |
    //|-------------------------------------------------------------------------------------|

    @Override
    public void createTopContent()
    {
        TextArea outputArea = new TextArea();

        outputArea.setMaxHeight(100);

        //For this  content of the parentView, we use the VBox "layout" to place the buttons.
        //It places views in a horizontal fashion one after another as shown in the gist diagram right below
        //---------------------------------------------------------------------------------------
        //|                                        HBox                                         |
        //|                      | Item A | |Item B| | Item C | |Item W|                        |
        //|                                                                                     |
        //|-------------------------------------------------------------------------------------|
        topContent = new HBox();
        topContent.getChildren().add(outputArea);
    }

    //Since our calculator has a gist layout of such for the left content:
    //-----------------------------
    //|          LEFT             |
    //|          CONTENT          |
    //|          ___              |
    //|         | + |  - ADD      |
    //|                           |
    //|         | - |  - SUBTRACT |
    //|                           |
    //|         | x |  - MULTIPLY |
    //|                           |
    //|         | / |  - DIVIDE   |
    //-----------------------------
    @Override
    public void createLeftContent()
    {
        Button addBtn = new Button("+");
        Button subBtn = new Button("-");
        Button multBtn = new Button("x");
        Button divBtn = new Button("/");

        //Here we are calling the Controller class methods to set ClickListeners to the left content buttons
        controller.setBtnClickListener(addBtn);
        controller.setBtnClickListener(subBtn);
        controller.setBtnClickListener(multBtn);
        controller.setBtnClickListener(divBtn);

        addBtn.setMaxWidth(Double.MAX_VALUE);
        subBtn.setMaxWidth(Double.MAX_VALUE);
        multBtn.setMaxWidth(Double.MAX_VALUE);
        divBtn.setMaxWidth(Double.MAX_VALUE);

        //For this left content of the parentView, we use the VBox "layout" to place the buttons.
        //It places views in a vertical fashion one after another as shown in the Left Content gist diagram right above
        //this method.
        leftContent = new VBox(4);
        leftContent.setPrefWidth(50);
        leftContent.setSpacing(10);
        leftContent.getChildren().addAll(addBtn, subBtn, multBtn, divBtn);
        leftContent.setStyle("-fx-background-color:#abcdef");
        leftContent.setSpacing(5);
        leftContent.setAlignment(Pos.CENTER);
        leftContent.setPadding(new Insets(0, 0, 5, 5));
    }

    @Override
    public void createCenterContent()
    {
        Button n0 = new Button("0");
        Button n1 = new Button("1");
        Button n2 = new Button("2");
        Button n3 = new Button("3");
        Button n4 = new Button("4");
        Button n5 = new Button("5");
        Button n6 = new Button("6");
        Button n7 = new Button("7");
        Button n8 = new Button("8");
        Button n9 = new Button("9");

        //Since our calculator has a gist layout of such for the center content:
        //-----------------------------
        //|          CENTER           |
        //|          CONTENT          |
        //|   ___    ___    ___       |
        //|  | 0 |  | 1 |  | 2 |      |
        //|                           |
        //|  | 3 |  | 4 |  | 5 |      |
        //|                           |
        //|  | 6 |  | 7 |  | 8 |      |
        //|                           |
        //|  | 9 |                    |
        //|----------------------------
        //We create this array to place the buttons in the desired locations
        //using a loop. Now you may be asking why I didn't create the buttons
        //using a loop and the answer is "I chose not to". But it is possible.

        Button buttons[][] = {{n0, n1, n2},
                {n3, n4, n5},
                {n6, n7, n8},
                {n9}};

        //For this center content of the parentView, we use the GridPane "layout" to place the buttons
        //The GridPane "layout" essentially allows you place views in "Row, Column Fashion"
        //For example, I could place buttons in as such (R, C):
        //Btn0 - (0,0)  Btn1 - (0,1)  Btn2 - (0,2)
        //Btn3 - (1,0)  Btn4 - (1,1)  Btn5 - (1,2)
        //Btn6 - (2,0)  Btn7 - (2,1)  Btn8 - (2,2)
        //Btn9 - (3,0)
        //-----------------------------
        //|          CENTER           |
        //|          CONTENT          |
        //|R  C-0     1      2        |
        //|| (0,0)  (0,1)  (0,2)      |
        //|0 | 0 |  | 1 |  | 2 |      |
        //|  (1,0)  (1,1)  (1,2)      |
        //|1 | 3 |  | 4 |  | 5 |      |
        //|  (2,0)  (2,1)  (2,2)      |
        //|2 | 6 |  | 7 |  | 8 |      |
        //|  (3,0)                    |
        //|3 | 9 |                    |
        //-----------------------------
        centerContent = new GridPane();
        centerContent.setHgap(10);
        centerContent.setVgap(10);



        //Here we place the buttons using multiple for loops in their desired locations
        for(int r=0; r<buttons.length; r++)
        {
            for(int c=0; c<buttons[r].length; c++)
            {

                buttons[r][c].setPrefHeight(40);
                buttons[r][c].setPrefWidth(40);
                //call Controller class object to set onClickListener for each button
                controller.setBtnClickListener(buttons[r][c]);
                centerContent.add(buttons[r][c], c, r);

            }
        }

        centerContent.setStyle("-fx-background-color:#abcdef");
        centerContent.setPadding(new Insets(5));
        centerContent.setAlignment(Pos.CENTER);
    }

    //Since our calculator has a gist layout of such for the right content:
    //-----------------------------
    //|          RIGHT            |
    //|          CONTENT          |
    //|          ___              |
    //|         | C |  - CLEAR    |
    //|                           |
    //|         | M |  - MEMORY   |
    //|                           |
    //|         | E |  - EXIT     |
    //|                           |
    //|         | R |  - RECALL   |
    //|                           |
    //|         |   |             |
    //|         | = |  - EQUALS   |
    //|         |   |             |
    //-----------------------------

    @Override
    public void createRightContent()
    {
        Button clearBtn = new Button("C");
        Button memoryBtn = new Button("M");
        Button emptyBtn = new Button("E");
        Button rButton = new Button("R");
        Button equalsBtn = new Button("=");

        clearBtn.setMaxWidth(Double.MAX_VALUE);
        memoryBtn.setMaxWidth(Double.MAX_VALUE);
        emptyBtn.setMaxWidth(Double.MAX_VALUE);
        rButton.setMaxWidth(Double.MAX_VALUE);
        equalsBtn.setMaxWidth(Double.MAX_VALUE);
        equalsBtn.setMaxHeight(Double.MAX_VALUE);
        equalsBtn.setPrefHeight(100);

        //call Controller class object to set onClickListener for each button
        controller.setClearClickListener(clearBtn);
        controller.setMemoryClickListener(memoryBtn);
        controller.setEClickListener(emptyBtn);
        controller.setRClickListener(rButton);
        controller.setEqualsClickListener(equalsBtn);

        //For this right content of the parentView, we use the VBox "layout" to place the buttons.
        //It places views in a vertical fashion one after another as shown in the Right Content gist diagram right above
        //this method.

        rightContent = new VBox(5);
        rightContent.setPrefWidth(50);
        rightContent.setSpacing(10);
        rightContent.getChildren().addAll(clearBtn, memoryBtn, emptyBtn, rButton, equalsBtn);
        rightContent.setStyle("-fx-background-color:#abcdef");
        rightContent.setSpacing(5);
        rightContent.setAlignment(Pos.CENTER);
        rightContent.setPadding(new Insets(5, 5, 5, 5));
    }


    @Override
    public void createScene()
    {
        //Here we initialize the Scene object by passing parentView (BorderPane) to be shown in the scene
        scene = new Scene(parentView);
    }

    @Override
    public void createStage()
    {
        stage = new Stage();
        // Add the scene to the Stage
        stage.setScene(scene);

        // Set the title of the Stage
        stage.setTitle("The Calculator");

        stage.setMaxWidth(300);
    }


    @Override
    public Stage getStage() {

        return stage;
    }
}
