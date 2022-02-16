import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.Todo;

public class DukeTest {
    @Test
    public void testTaskSizeParser_emptyString() {
        assertEquals("", Parser.parseTaskSize(1));
    }

    @Test
    public void testTaskFileFormat() throws DukeException {
        Task task = new Todo("Todo Test");
        assertEquals("T | 0 | Todo Test", task.getSaveFormat());
    }
}
