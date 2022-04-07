package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;

public class ParserTest {
    @Test
    public void dummyTest() {

        assertEquals(2, 2);
    }

    @Test
    public void parserTestWithDeadlineEmptyDescription() {
        String input = "deadline";
        Parser inputParser = new Parser(input);
        Command currCommand = null;
        try {
            currCommand = inputParser.parse();
        } catch (DukeException e) {
            assertEquals("Please enter Task Description", e.getMessage());
        }
    }
    @Test
    public void parserTestWithInvalidCommand() {
        String input = "gibberish";
        Parser inputParser = new Parser(input);
        Command currCommand = null;
        try {
            currCommand = inputParser.parse();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but i don't know what that means :-(", e.getMessage());
        }
    }
}
