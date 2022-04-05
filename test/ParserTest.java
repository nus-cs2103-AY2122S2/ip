import duke.task.IncompleteArgumentException;
import duke.task.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parsingDeadline_validArgument_success() throws Exception {
        String[] strArr = {"read book", "tmr"};
        assertArrayEquals(strArr, new Parser().parseDeadline("read book /by tmr"));
    }

    @Test
    public void parsingEvent_validArgument_success() throws Exception {
        String[] strArr = {"read book", "night"};
        assertArrayEquals(strArr, new Parser().parseEvent("read book /at night"));
    }

    @Test
    public void parsingDeadline_incompleteArgument_exceptionThrown() {
        try {
            String[] strArr = {"read book", "tmr"};
            assertArrayEquals(strArr, new Parser().parseDeadline("read book"));
        } catch (IncompleteArgumentException ex) {
            assertEquals("Incomplete Argument", ex.getMessage());
        }
    }
}
