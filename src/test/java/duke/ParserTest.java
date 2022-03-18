package duke;

import org.junit.jupiter.api.Test;

import static duke.TaskList.taskList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The ParserTest class, containing tests for the Parser class.
 *
 * @author Jet Tan
 */
public class ParserTest {
    @Test
    public void testParse_todoSuccess() {
        try {
            Storage.initFiles();
            Parser.process("todo hi");
            assertEquals("[[T][ ] hi]", taskList.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_eventExceptionThrown() {
        try {
            Storage.initFiles();
            Parser.process("event");
            fail();
        } catch (Exception e) {
            assertEquals("Usage: event <description> /at <YYYY-MM-DD> <24-hr time, e.g. 2359>", e.getMessage());
        }
    }
}
