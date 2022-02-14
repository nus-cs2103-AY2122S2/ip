package test.java;

import mike.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void removeCommandFromInput_normalTestInput_inputWithoutCommand() {
        assertEquals("eat and sleep",
                new Parser("todo eat and sleep").removeCommandFromString());
        assertEquals("Do homework /by 2022-10-10",
                new Parser("deadline Do homework /by 2022-10-10").removeCommandFromString());
    }

    @Test
    public void removeCommandFromInput_emptyTestInput_emptyString() {
        assertEquals("",
                new Parser("").removeCommandFromString());
    }

}
