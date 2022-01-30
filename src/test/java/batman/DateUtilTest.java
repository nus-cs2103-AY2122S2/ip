package batman;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import batman.parser.Parser;
import batman.tasks.TaskList;

public class DateUtilTest {
    private StringBuilder result;
    private String expected;
    private String input;

    @Test
    public void parser_invalidInput_incorrectDateResult() {
        input = "event project meeting " + "/at Sunday";
        result = Parser.parseInput(input);
        expected = "Get the date format right!\ndd/MM/yyyy HH:mm OR yyyy-MM-dd HH:mm\n";
        assertEquals(expected, result.toString());
    }

    @Test
    public void parser_validInput_correctDateResult() {
        TaskList taskList = new TaskList();
        input = "event project meeting /at 1/01/2022 11:00";
        result = Parser.parseInput(input);
        expected = "Got it. Task added:\n  " + taskList.get(0)
                + "\nNow you have " + taskList.getSize() + " tasks in the list.\n";
        assertEquals(expected, result.toString());
    }

}
