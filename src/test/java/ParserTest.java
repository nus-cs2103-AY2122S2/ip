import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    }
}