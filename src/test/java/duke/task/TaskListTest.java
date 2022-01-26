package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void size_empty_zeroReturned() {
        assertEquals(new TaskList().size(), 0);
    }

    @Test
    public void size_nonEmpty() {
        TaskList tasks = new TaskList();

        tasks.addTask(new Todo("first"));
        tasks.addTask(new Todo("second"));
        tasks.addTask(new Todo("third"));

        assertEquals(tasks.size(), 3);

        try {
            tasks.removeTask(2);

            tasks.addTask(new Task("new 1"));
            tasks.addTask(new Task("new 2"));

            assertEquals(tasks.size(), 4);
        } catch (DukeException e) {
            // Shouldn't happen
            assert false;
        }
    }

    @Test
    public void searchTasks() {
        TaskList tasks = new TaskList();

        tasks.addTask(new Todo("first"));
        tasks.addTask(new Todo("second"));
        tasks.addTask(new Todo("third"));
        tasks.addTask(new Task("new 1"));
        tasks.addTask(new Task("new 2"));

        assertEquals(tasks.searchTasks("new").size(), 2);
        assertEquals(tasks.searchTasks("ir").size(), 2);
        assertEquals(tasks.searchTasks("fir").size(), 1);
        assertEquals(tasks.searchTasks("fourth").size(), 0);
    }

    @Test
    public void getTask_indexOutOfBounds_exceptionThrown() {
        TaskList tasks = new TaskList();

        tasks.addTask(new Todo("first"));
        tasks.addTask(new Todo("second"));
        tasks.addTask(new Todo("third"));

        assertThrows(DukeException.class, () -> tasks.getTask(-1));
        assertThrows(DukeException.class, () -> tasks.getTask(3));
    }

    @Test
    public void removeTask_indexOutOfBounds_exceptionThrown() {
        TaskList tasks = new TaskList();

        tasks.addTask(new Todo("first"));
        tasks.addTask(new Todo("second"));
        tasks.addTask(new Todo("third"));

        assertThrows(DukeException.class, () -> tasks.removeTask(-1));
        assertThrows(DukeException.class, () -> tasks.removeTask(3));
    }

    @Test
    public void mark_indexOutOfBounds_exceptionThrown() {
        TaskList tasks = new TaskList();

        tasks.addTask(new Todo("first"));
        tasks.addTask(new Todo("second"));
        tasks.addTask(new Todo("third"));

        assertThrows(DukeException.class, () -> tasks.markTask(-1));
        assertThrows(DukeException.class, () -> tasks.markTask(3));
    }

    @Test
    public void unmark_indexOutOfBounds_exceptionThrown() {
        TaskList tasks = new TaskList();

        tasks.addTask(new Todo("first"));
        tasks.addTask(new Todo("second"));
        tasks.addTask(new Todo("third"));

        assertThrows(DukeException.class, () -> tasks.unmarkTask(-1));
        assertThrows(DukeException.class, () -> tasks.unmarkTask(3));
    }
}
