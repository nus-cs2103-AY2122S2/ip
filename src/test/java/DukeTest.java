import duke.parser.StorageParser;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Task;
import duke.task.Deadline;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testTask() {
        Task deadline = new Deadline("deadline return book /by 20/12/2022 1800");
        assertEquals(deadline.toString(), "[D][ ] return book (by: Dec 20 2022 6.00 pm)");

        Task event = new Event("event return book /at 20/12/2022");
        assertEquals(event.toString(), "[E][ ] return book (at: Dec 20 2022)");

        Task todo = new Todo("todo return book");
        assertEquals(todo.toString(), "[T][ ] return book");
    }

    @Test
    public void testNewLineStorageParser() {
        Task deadline = new Task("deadline return book /by 20/12/2022 1800");
        StorageParser parser = new StorageParser(deadline);
        assertEquals(parser.toString(), "\n");
    }
}
