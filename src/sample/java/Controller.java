package sample.java;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

//This Controller class is responsible for controlling the behavior of the application such as the action
//for btnOnClickListener

public class Controller extends Pref implements Controls {

    private TextArea o;
    private double curr_result;
    private boolean isShowingResult = false;
    private ArrayList<Double> storedResults = new ArrayList<>();
    private int showingStoredResultIndex = -1;

    public Controller(TextArea o)
    {
        this.o = o;
    }

    @Override
    public void setBtnClickListener(Button btn) {
        btn.setOnAction(actionEvent -> {
            update0Msg(btn.getText());
        });
    }

    @Override
    public void setClearClickListener(Button clear) {
        clear.setOnAction(actionEvent -> {
            clearOMsg();
        });
    }

    @Override
    public void setMemoryClickListener(Button memory) {
        memory.setOnAction(actionEvent -> {
            if (isShowingResult)
                storedResults.add(curr_result);

        });
    }

    @Override
    public void setEClickListener(Button E) {
        E.setOnAction(actionEvent -> {
            clearOMsg();
            //Since this class is a child of (extends) Pref abstract class and we set the
            //current viewModel class(recall ViewPrefs class) by calling setCurrPref in
            //Main class when setting the Stage, we can now refer to that Stage and exit
            //the window. The close method is part of the Stage class.
            getCurrPref().close();
        });
    }

    @Override
    public void setRClickListener(Button R) {
        R.setOnAction(actionEvent -> {

            String prevResult = getPreviousResult();

            if (prevResult!=null)
                writeOMsg(prevResult);
        });
    }

    @Override
    public String getPreviousResult() {

        //Control statements used to point to the proper stored location of the result
        //in storedResults ArrayList.

        if(isShowingResult) {
            showingStoredResultIndex--;
            if (showingStoredResultIndex<0)
                showingStoredResultIndex = storedResults.size() - 1;
        }

        else
            showingStoredResultIndex = storedResults.size() - 1;

        //In the case that storedResults contains results
        if (showingStoredResultIndex>=0)
            return storedResults.get(showingStoredResultIndex).toString();


        return null;
    }

    @Override
    public void setEqualsClickListener(Button Equals) {
        Equals.setOnAction(actionEvent -> {
            String curr_output_msg = getOMsg();
            if (!curr_output_msg.isEmpty()) {
                writeOMsg(getComputedVal(curr_output_msg));
                isShowingResult = true;
            }
        });
    }

    //used to update current text in outputArea TextArea
    @Override
    public void update0Msg(String msg) {
        isShowingResult = false;
        o.setText(getOMsg()+msg);
    }

    //used to "clear" and "push" new text in/to outputArea TextArea
    @Override
    public void writeOMsg(String msg) {
        o.setText(msg);
    }

    @Override
    public String getComputedVal(String input) {
        String output;

        //if input String does not contain any of the arithmetic operators, then set incoming input String as the output area
        //val
        if (!(input.contains("+")||input.contains("-")||input.contains("x")||input.contains("/")))
            output = input;

        else {

            //StringBuilder objects used to store upper and lower bounds of arithmetic operation
            //Ex. 12 + 33 Upperbound - 12,   Lowerbound - 13
            StringBuilder topTempVal = new StringBuilder();
            StringBuilder bottomTempVal = new StringBuilder();

            //Used to keep track of the the curr arithmetic operation operator
            char curr_operation = ' ';
            //Boolean used to determine when to start computing
            boolean operationInProgress = false;

            int charPos = 0;
            for (Character c : input.toCharArray()) {
                if (!(c.equals('+')||c.equals('-')||c.equals('x')||c.equals('/'))) {
                    //If we don't detect an arithmetic operator yet, we check to see if we have already detected an operator
                    if (operationInProgress)
                    {
                        //if we already detected an operator, append character to BottomBound StringBuilder
                        bottomTempVal.append(c);
                        //If we haven't reached the last character from input, check to see if the next character is another arithmetic operator
                        if (charPos<input.length()-1) {
                            char nextC = input.charAt(charPos+1);
                            //if the next character is another arithmetic operator, then compute the arithmetic operation for the currently stored
                            //upperbound and lowerbound StringBuilder values and append result to upperbound StringBuilder
                            //Ex. curr_operation = + | input = 22+32x50 | upperBound = 22| lowerBound = 32 |next operator is x.
                            //So compute 22+32 = 54 & append 54 to upperBound
                            if (nextC == '+' || nextC == '-' || nextC == 'x' || nextC == '/') {
                                operationInProgress = false;
                                String tempComputedVal = compute(curr_operation, Double.valueOf(topTempVal.toString()), Double.valueOf(bottomTempVal.toString()));
                                bottomTempVal.setLength(0);
                                topTempVal.setLength(0);
                                topTempVal.append(tempComputedVal);
                            }
                        }
                        //Otherwise, If we reached the last character from input, then compute the arithmetic operation for the currently stored
                        //upperbound and lowerbound StringBuilder values and append result to upperbound StringBuilder
                        //Ex. curr_operation = x | input = 22+32x50 | upperBound = 54  |lowerBound = 50
                        //So computer 54x50 = 2700 & append 2700 to upperBound
                        else if(charPos == input.length() - 1)
                        {
                            operationInProgress = false;
                            String tempComputedVal = compute(curr_operation, Double.valueOf(topTempVal.toString()), Double.valueOf(bottomTempVal.toString()));
                            bottomTempVal.setLength(0);
                            topTempVal.setLength(0);
                            topTempVal.append(tempComputedVal);
                        }

                    }
                    else
                        //If we didn't already detect an arithmetic operator, then append character to upperBound
                        topTempVal.append(c);
                }
                else {
                    //If we detect a new operator, set curr_operation to operator and
                    curr_operation = c;
                    operationInProgress = true;
                }

                charPos++;
            }

            //Set value in upperBound StringBuilder to output
            output = topTempVal.toString();
            topTempVal.setLength(0);
        }
        //Store most recent output to be used to store in storedResults ArrayList if user clicks Memory button to store
        //recent result;
        curr_result = Double.valueOf(output);
        return output;
    }

    @Override
    public String compute(char operator, double upperBound, double lowerBound) {
        String output = "";
        switch (operator)
        {
            case '+':
                double addVal = (upperBound + lowerBound);
                output = String.valueOf(addVal);
                break;
            case '-':
                double subVal = (upperBound - lowerBound);
                output = String.valueOf(subVal);
                break;
            case 'x':
                double multVal = (upperBound * lowerBound);
                output = String.valueOf(multVal);
                break;
            case '/':
                double divVal = (upperBound / lowerBound);
                output = String.valueOf(divVal);
                break;
        }
        return output;
    }

    //Get message from outputArea
    @Override
    public String getOMsg() {
        return o.getText();
    }

    //Reset all variables storing values
    @Override
    public void clearOMsg() {
        isShowingResult = false;
        o.clear();
        storedResults.clear();
        showingStoredResultIndex = -1;
    }
}
