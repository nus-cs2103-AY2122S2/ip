package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.add.DeadlineCommand;
import duke.exception.DukeException;

class ParserTest {

    @Test
    public void parseCommand_todoTask_success() {
        try {
            String userInput = "todo sometesting";
            Command c = Parser.parseCommand(userInput);

            assertEquals("todo", c.getCommandWord());

        } catch (DukeException e) {
            fail("Unexpected exception thrown!");
        }
    }


    @Test
    public void parseCommand_deadlineTask_exceptionThrown() {
        try {
            String userInput = "deadline sometesting \\by june 6 1230";
            Command c = Parser.parseCommand(userInput);

            fail("Expected exception not thrown!");

        } catch (DukeException e) {
            assertEquals(DeadlineCommand.COMMAND_FORMAT, e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        try {
            String userInput = "random";
            Command c = Parser.parseCommand(userInput);

            fail("Expected exception not thrown!");

        } catch (DukeException e) {
            assertEquals("", e.getMessage());
        }
    }
}
