package sample.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    @Test
    void init()
    {
        Controller c = new Controller(null);
        double actual = Double.parseDouble(c.getComputedVal("5+6x11+19"));
        assertEquals(140.0, actual, "Output Result For Arithmetic Operation");
    }

}