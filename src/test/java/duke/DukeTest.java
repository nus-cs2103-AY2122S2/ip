package duke;

import org.junit.jupiter.api.Test;
import seedu.duke.DukeException;
import seedu.duke.Parser;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeTest {

    @Test
    public void parseTest() throws DukeException {
        Parser p = new Parser();
        assertEquals("list", p.splitCmd("list")[0]);
    }

    @Test
    public void testDate() throws IOException {
        Storage s = new Storage();
        assertThrows(ParseException.class, () -> {
            s.parse("this is not a date");
        });
    }
}
