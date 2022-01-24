package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void mark_unmarked() {
        Task task = new Task("taskName");
        task.mark();
        assertEquals(task.isDone(), true);
    }

    @Test
    public void mark_marked() {
        Task task = new Task("taskName");
        task.mark();
        task.mark();
        assertEquals(task.isDone(), true);
    }

    @Test
    public void unmark_unmarked() {
        Task task = new Task("taskName");
        task.unmark();
        assertEquals(task.isDone(), false);
    }

    @Test
    public void unmark_marked() {
        Task task = new Task("taskName");
        task.mark();
        task.unmark();
        assertEquals(task.isDone(), false);
    }

    @Test
    public void toString_marked() {
        Task task = new Task("taskName");
        task.mark();
        assertEquals(task.toString(), "[X] taskName");
    }

    @Test
    public void toString_unmarked() {
        Task task = new Task("taskName");
        assertEquals(task.toString(), "[ ] taskName");
    }

}