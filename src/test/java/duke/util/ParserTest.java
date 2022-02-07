package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void getCommand_Todo_success() {
        String originalInput = "todo buy shampoo";
        Parser parserUnderTest = new Parser(originalInput);
        assertEquals("todo", parserUnderTest.getCommand());
    }

    @Test
    public void inputArray_Todo_success() {
        String originalInput = "todo buy shampoo";
        Parser parserUnderTest = new Parser(originalInput);
        String[] expected = new String[3];
        expected[0] = "todo";
        expected[1] = "buy";
        expected[2] = "shampoo";
        assertEquals(expected[0], parserUnderTest.getInputArray()[0]);
        assertEquals(expected[1], parserUnderTest.getInputArray()[1]);
        assertEquals(expected[2], parserUnderTest.getInputArray()[2]);
    }
}
