package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskTest {

    @Test
    public void mark_unmarked() {
        Task task = new Task("taskName");
        task.setMarked(true);
        assertEquals(task.isMarked(), true);
    }

    @Test
    public void mark_marked() {
        Task task = new Task("taskName");
        task.setMarked(true);
        task.setMarked(true);
        assertEquals(task.isMarked(), true);
    }

    @Test
    public void unmark_unmarked() {
        Task task = new Task("taskName");
        task.setMarked(false);
        assertEquals(task.isMarked(), false);
    }

    @Test
    public void unmark_marked() {
        Task task = new Task("taskName");
        task.setMarked(true);
        task.setMarked(false);
        assertEquals(task.isMarked(), false);
    }

    @Test
    public void toString_marked() {
        Task task = new Task("taskName");
        task.setMarked(true);
        assertEquals(task.toString(), "[X] taskName");
    }

    @Test
    public void toString_unmarked() {
        Task task = new Task("taskName");
        assertEquals(task.toString(), "[ ] taskName");
    }

}
