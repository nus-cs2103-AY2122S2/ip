package puke.parser;

import puke.exception.PukeException;
import puke.task.TaskList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private TaskList tasks = new TaskList();
    private Parser parser = new Parser();

    @Test
    public void processInput_invalidInput_exceptionThrow() {
        try {
            assertEquals(0, parser.processInput("hello", tasks));
        } catch (PukeException e) {
            assertEquals("Are you sure you're making sense?", e.getMessage());
        }
    }

    @Test
    public void processInput_markNonNumber_exceptionThrow() {
        try {
            assertEquals(0, parser.processInput("mark hello", tasks));
        } catch (PukeException e) {
            assertEquals("I'll need a valid task number for it..", e.getMessage());
        }
    }

    @Test
    public void processInput_bye_success() {
        try {
            assertEquals(null, parser.processInput("bye", tasks));
        } catch (PukeException e) {
            fail();
        }
    }
}
