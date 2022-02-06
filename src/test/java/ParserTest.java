import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testParseDate_success() throws DukeException {
        assertEquals(LocalDate.of(2022, 1, 23), Parser.parseDate("2022-01-23"));
    }

    @Test
    public void testParseDate_fail() {
        try {
            assertEquals(LocalDate.of(2022, 1, 23), Parser.parseDate("2022/01/23"));
            fail();
        } catch (DukeException e) {
            assertEquals(ErrorMessage.MESSAGE_INVALID_DATE_FORMAT, e.getMessage());
        }

        try {
            assertEquals(LocalDate.of(2022, 1, 23), Parser.parseDate("2022-00-01"));
            fail();
        } catch (DukeException e) {
            assertEquals(ErrorMessage.MESSAGE_INVALID_DATE_VALUE, e.getMessage());
        }
    }

    @Test
    public void testParseCommand() throws DukeException {
        assertEquals(new ExitCommand().isExit(), Parser.parseCommand("bye").isExit());
    }
}
