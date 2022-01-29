package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_addToDoCommand_success() throws DukeException {
        Parser parser = new Parser();
        String commandLine = "todo Test ToDo Task";

        Command command = parser.parse(commandLine);
        assertTrue(command instanceof AddToDoCommand);
    }

    @Test
    public void parse_addToDoCommand_missingDescription_exceptionThrown() {
        Parser parser = new Parser();
        String commandLine = "todo";

        try {
            Command command = parser.parse(commandLine);
            assertTrue(command instanceof AddToDoCommand);
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            String errorMessage = "The description of a todo cannot be empty!";
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void parse_addDeadlineCommand_success() throws DukeException {
        Parser parser = new Parser();
        String commandLine = "deadline Test Deadline Task /by 2022-01-30 2359";

        Command command = parser.parse(commandLine);
        assertTrue(command instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_addDeadlineCommand_missingDescription_exceptionThrown() {
        Parser parser = new Parser();
        String commandLine = "deadline /by 2022-01-30 2359";

        try {
            Command command = parser.parse(commandLine);
            assertTrue(command instanceof AddToDoCommand);
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            String errorMessage = "INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description of a deadline cannot be empty!";
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void parse_addDeadlineCommand_wrongCommand_exceptionThrown() {
        Parser parser = new Parser();
        String commandLine = "deadline Test Deadline Task /at 2022-01-30 2359";

        try {
            Command command = parser.parse(commandLine);
            assertTrue(command instanceof AddDeadlineCommand);
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            String errorMessage = "WRONG COMMAND"
                    + System.lineSeparator() + "\t"
                    + "Enter /by before specifying the date/time!";
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void parse_addDeadlineCommand_missingDescriptionAndWrongCommand_exceptionThrown() {
        Parser parser = new Parser();
        String commandLine = "deadline /at 2022-01-30 2359";

        try {
            Command command = parser.parse(commandLine);
            assertTrue(command instanceof AddToDoCommand);
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            String errorMessage = "INCOMPLETE & WRONG COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description of a deadline cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "Enter /by before specifying the date/time!";
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void parse_addEventCommand_success() throws DukeException {
        Parser parser = new Parser();
        String commandLine = "event Test Event Task /at 2022-01-31 7-10pm";

        Command command = parser.parse(commandLine);
        assertTrue(command instanceof AddEventCommand);
    }

    @Test
    public void parse_addEventCommand_missingDescription_exceptionThrown() {
        Parser parser = new Parser();
        String commandLine = "event /at 2022-01-31 7-10pm";

        try {
            Command command = parser.parse(commandLine);
            assertTrue(command instanceof AddEventCommand);
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            String errorMessage = "INCOMPLETE COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description of an event cannot be empty!";
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void parse_addEventCommand_wrongCommand_exceptionThrown() {
        Parser parser = new Parser();
        String commandLine = "event Test Event Task /by 2022-01-31 7-10pm";

        try {
            Command command = parser.parse(commandLine);
            assertTrue(command instanceof AddEventCommand);
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            String errorMessage = "WRONG COMMAND"
                    + System.lineSeparator() + "\t"
                    + "Enter /at before specifying the date/time!";
            assertEquals(errorMessage, e.getMessage());
        }
    }

    @Test
    public void parse_addEventCommand_missingDescriptionAndWrongCommand_exceptionThrown() {
        Parser parser = new Parser();
        String commandLine = "event /by 2022-01-31 7-10pm";

        try {
            Command command = parser.parse(commandLine);
            assertTrue(command instanceof AddEventCommand);
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            String errorMessage = "INCOMPLETE & WRONG COMMAND"
                    + System.lineSeparator() + "\t"
                    + "The description of an event cannot be empty!"
                    + System.lineSeparator() + "\t"
                    + "Enter /at before specifying the date/time!";
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
