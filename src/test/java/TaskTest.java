import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.Event;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.ToDos;
import duke.Ui;

/**
 * Test class for testing task class
 */
public class TaskTest {
    private Storage storage;
    private Ui ui;
    private TaskList listOfTasks;

    /**
     * Tests setDone method of Task class.
     */
    @Test
    public void setDoneTest() {
        Task t = new Task("buy book");
        t.setDone();
        assertEquals("1", t.getIsDone());
    }
    /**
     * Tests getType method of Task class.
     */
    @Test
    public void getTypeTest() {
        ToDos t = new ToDos("buy book");
        assertEquals("T", t.getType());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        Event e = new Event("buy book", LocalDateTime.parse("12/09/2019 1800", dtf));
        assertEquals("E", e.getType());
        Deadline d = new Deadline("buy book", LocalDateTime.parse("12/09/2019 1800", dtf));
        assertEquals("D", d.getType());
    }
}
