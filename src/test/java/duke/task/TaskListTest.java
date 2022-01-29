package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void mark_validId_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("description"));
        assertDoesNotThrow(() -> tasks.mark(0));
    }

    @Test
    public void mark_invalidId_exceptionThrown() {
        TaskList tasks = new TaskList();
        assertThrows(DukeException.class, () -> tasks.mark(0));
    }

    @Test
    public void unmark_validId_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("description"));
        assertDoesNotThrow(() -> tasks.unmark(0));
    }

    @Test
    public void unmark_invalidId_exceptionThrown() {
        TaskList tasks = new TaskList();
        assertThrows(DukeException.class, () -> tasks.unmark(0));
    }
}