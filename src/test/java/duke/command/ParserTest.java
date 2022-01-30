package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeInvalidCommandException;

public class ParserTest {
    @Test
    public void testFactory_valid_success() throws DukeInvalidCommandException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("mark a") instanceof MarkCommand);
        assertTrue(Parser.parse("unmark a") instanceof MarkCommand);
        assertTrue(Parser.parse("todo a") instanceof CreateCommand);
        assertTrue(Parser.parse("deadline a") instanceof CreateCommand);
        assertTrue(Parser.parse("event a") instanceof CreateCommand);
        assertTrue(Parser.parse("delete a") instanceof DeleteCommand);
        assertTrue(Parser.parse("upcoming a") instanceof UpcomingCommand);
        assertTrue(Parser.parse("between a") instanceof BetweenCommand);
        assertTrue(Parser.parse("schedule a") instanceof ScheduleCommand);
        assertTrue(Parser.parse("find a") instanceof FindCommand);
    }

    @Test
    public void testFactory_alphabetCases_success() throws DukeInvalidCommandException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("Bye") instanceof ExitCommand);
        assertTrue(Parser.parse("ByE") instanceof ExitCommand);
        assertTrue(Parser.parse("BYE") instanceof ExitCommand);
    }

    @Test
    public void testFactory_invalid_exceptionRaised() {
        try {
            Parser.parse("notcommand");
            fail();
        } catch (DukeInvalidCommandException ex) {
            assertEquals("No such command: notcommand", ex.getMessage());
        }
    }
}
