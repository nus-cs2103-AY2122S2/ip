package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getCommandTodoTest() {
        String originalInput = "todo buy shampoo";
        Parser parserUnderTest = new Parser(originalInput);
        assertEquals("todo", parserUnderTest.getCommand());
    }

    @Test
    public void inputArrayTodoTest() {
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
