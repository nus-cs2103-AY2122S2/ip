import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sana.Parser;
import sana.task.Deadline;
import sana.task.Event;


public class SanaTest {
    @Test
    public void deadlineTest() {
        Deadline testDeadline = new Deadline("testDeadline", true, "2022-01-01");
        String deadlineToString = "[D][X] testDeadline (by: Jan 1 2022)";
        assertEquals(deadlineToString, testDeadline.toStringFromList());
    }

    @Test
    public void eventTest() {
        Event testEvent = new Event("testEvent", false, "2022-02-02");
        String eventToString = "[E][ ] testEvent (at: Feb 2 2022)";
        assertEquals(eventToString, testEvent.toStringFromList());
    }

    @Test
    public void parserTest() {
        String command = "todo test Sana";
        String[] parseResult = new String[] {"todo", "test Sana"};
        Parser parser = new Parser();
        assertArrayEquals(parseResult, parser.parseCommand(command));
    }

}
