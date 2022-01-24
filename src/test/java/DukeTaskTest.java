
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.ToDoTask;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTaskTest {
    @Test
    public void createTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        TaskList tl = new TaskList();
        tl.addTask("New Todo Task", false, null, 0);
        assertEquals(1, tl.getSize());
        tl.addTask("New Deadline Task", false, LocalDate.parse("11/1/1999", formatter), 1);
        assertEquals(2, tl.getSize());
        tl.addTask("New Event Task", false, LocalDate.parse("11/1/1999", formatter), 2);
        assertEquals(3, tl.getSize());
    }

    @Test
    public void deleteTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        TaskList tl = new TaskList();
        tl.addTask("New Todo Task", false, null, 0);
        tl.addTask("New Deadline Task", false, LocalDate.parse("11/1/1999", formatter), 1);
        tl.addTask("New Event Task", false, LocalDate.parse("11/1/1999", formatter), 2);
        tl.removeTask(0);
        assertEquals(2, tl.getSize());
        tl.removeTask(0);
        assertEquals(1, tl.getSize());
        tl.removeTask(0);
        assertEquals(0, tl.getSize());
    }

    @Test
    public void getLast() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        TaskList tl = new TaskList();
        tl.addTask("New Todo Task", false, null, 0);
        assertEquals("New Todo Task", tl.getLast().getTaskName());
        tl.addTask("New Deadline Task", false, LocalDate.parse("11/1/1999", formatter), 1);
        assertEquals("New Deadline Task", tl.getLast().getTaskName());
        tl.addTask("New Event Task", false, LocalDate.parse("11/1/1999", formatter), 2);
        assertEquals("New Event Task", tl.getLast().getTaskName());
    }
}
