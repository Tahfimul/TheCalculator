package sample;

import javafx.scene.control.Button;

//This interface class contains all methods that we want implemented in the Controller class

public interface Controls {

    void setBtnClickListener(Button btn);

    void setClearClickListener(Button clear);

    void setMemoryClickListener(Button memory);

    void setEClickListener(Button E);

    void setRClickListener(Button R);

    void setEqualsClickListener(Button Equals);

    void update0Msg(String msg);

    void writeOMsg(String msg);

    String getOMsg();

    void clearOMsg();

    String getComputedVal(String input);

    String compute(char operator, double upperBound, double lowerBound);

    String getPreviousResult();

}
