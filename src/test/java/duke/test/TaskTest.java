package duke.test;

import static org.junit.jupiter.api.Assertions.*;

import duke.exceptions.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskTest {
    @Test
    public void deadlineTest() {
        LocalDate by = LocalDate.parse("2019-01-01");
        Deadline d = new Deadline("borrow book", by);
        String expected = "[D] [ ] borrow book (by: Jan 1 2019)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void EventTest() {
        LocalDate at = LocalDate.parse("2019-01-01");
        Event e = new Event("borrow book", at);
        String expected = "[E] [ ] borrow book (at: Jan 1 2019)";
        assertEquals(expected, e.toString());
    }

    @Test
    public void toDoTest() {
        Todo t = new Todo("borrow book");
        String expected = "[T] [ ] borrow book";
        assertEquals(expected, t.toString());
    }

    @Test
    public void ofTest() throws InvalidTaskException {
        Task task;

        LocalDate by = LocalDate.parse("2019-01-01");
        Deadline d = new Deadline("borrow book", by);
        task = Task.of("deadline borrow book /by 2019-01-01");
        assertEquals(d.toString(), task.toString());

        LocalDate at = LocalDate.parse("2019-01-01");
        Event e = new Event("borrow book", at);
        task = Task.of("event borrow book /at 2019-01-01");
        assertEquals(e.toString(), task.toString());

        Todo t = new Todo("borrow book");
        task = Task.of("todo borrow book");
        assertEquals(t.toString(), task.toString());
    }

    @Test
    public void invalidOfTest() {
        assertThrows(InvalidTaskException.class, () -> Task.of("bla bla"));
        assertThrows(InvalidTaskException.class, () -> Task.of("deadline borrow book"));
        assertThrows(InvalidTaskException.class, () -> Task.of("event borrow book"));
    }

    @Test
    public void fromCsvTest() throws InvalidTaskException {
        LocalDate by = LocalDate.parse("2019-01-01");
        Deadline d = new Deadline("borrow book", by);
        Task task = Task.fromCsv("D, ,borrow book,2019-01-01", ",");
        assertEquals(d.toString(), task.toString());

        LocalDate at = LocalDate.parse("2019-01-01");
        Event e = new Event("borrow book", at);
        task = Task.fromCsv("E, ,borrow book,2019-01-01", ",");
        assertEquals(e.toString(), task.toString());

        Todo t = new Todo("borrow book");
        task = Task.fromCsv("T, ,borrow book", ",");
        assertEquals(t.toString(), task.toString());
    }

    @Test
    public void invalidFromCsvTest() {
        assertThrows(InvalidTaskException.class, () -> Task.fromCsv("bla,bla", ","));
        assertThrows(InvalidTaskException.class, () -> Task.fromCsv("E, ,borrow book", ","));
        assertThrows(InvalidTaskException.class, () -> Task.fromCsv("T,borrow book", ","));
    }

    @Test
    public void markTest() {
        Todo t = new Todo("borrow book");
        t.markAsCompleted();
        String expected = "x";
        assertEquals(expected, t.getStatusIcon());

        t.markAsUncompleted();
        expected = " ";
        assertEquals(expected, t.getStatusIcon());
    }
}
