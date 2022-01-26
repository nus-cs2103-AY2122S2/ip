package duke.main;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    void getTask_invalidIndex_exceptionThrown() {
        TaskList testList = new TaskList();
        Exception exception = assertThrows(DukeException.class, () ->
                testList.getTask(0));
        assertEquals(exception.getMessage(), DukeException.ERROR_INVALID_INDEX);
    }

    @Test
    void deleteTask_invalidIndex_exceptionThrown() {
        TaskList testList = new TaskList();
        Exception exception = assertThrows(DukeException.class, () ->
                testList.delete(-1));
        assertEquals(exception.getMessage(), DukeException.ERROR_INVALID_INDEX);
    }
}
