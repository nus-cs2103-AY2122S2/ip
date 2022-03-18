package juke;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void correctTodoOutput() {
        String inputString = "todo homework";
        String outputString = Parser.getResponse(inputString, new ArrayList<>(10));
        String expectedOutput = "\n" +
                "     Got it. I've added this task: \n" +
                "       [T][ ] homework\n" +
                "     Now you have 1 tasks in the list.\n" +
                "\n";
        assert(outputString.equals(expectedOutput));
    }

    @Test
    void incorrectTodoOutput() {
        String inputString = "todo housework";
        String outputString = Parser.getResponse(inputString, new ArrayList<>(10));
        String expectedOutput = "\n" +
                "     Got it. I've added this task: \n" +
                "       [T][ ] housewerk\n" +
                "     Now you have 1 tasks in the list.\n" +
                "\n";
        assert(!outputString.equals(expectedOutput));
    }
}
