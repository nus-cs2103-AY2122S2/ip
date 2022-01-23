
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
        tl.addTask(new ToDoTask("New Todo Task"));
        assertEquals(1, tl.getSize());
        tl.addTask(new DeadlineTask("New Event Task", LocalDate.parse("11/1/1999", formatter)));
        assertEquals(2, tl.getSize());
        tl.addTask(new EventTask("New Event Task", LocalDate.parse("11/1/1999", formatter)));
        assertEquals(3, tl.getSize());
    }

    @Test
    public void deleteTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        TaskList tl = new TaskList();
        tl.addTask(new ToDoTask("New Todo Task"));
        tl.addTask(new DeadlineTask("New Deadline Task", LocalDate.parse("11/1/1999", formatter)));
        tl.addTask(new EventTask("New Event Task", LocalDate.parse("11/1/1999", formatter)));
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
        ToDoTask todo_nt = new ToDoTask("New Todo Task");
        tl.addTask(todo_nt);
        assertEquals(tl.getLast(), todo_nt);
        DeadlineTask deadline_nt = new DeadlineTask("New Deadline Task", LocalDate.parse("11/1/1999", formatter));
        tl.addTask(deadline_nt);
        assertEquals(tl.getLast(), deadline_nt);
        EventTask event_nt = new EventTask("New Event Task", LocalDate.parse("11/1/1999", formatter));
        tl.addTask(event_nt);
        assertEquals(tl.getLast(), event_nt);
    }
}
