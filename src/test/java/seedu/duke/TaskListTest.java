package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    @DisplayName("Adding task to TaskList should increase size of arraylist by 1")
    void testAdd() {
        Task newTask = new ToDo("Testing");
        TaskList newTaskList = taskList.add(newTask);
        assertEquals(1, newTaskList.getNumberOfTasks(),
                "Add in TaskList should work");
    }

    @Test
    @DisplayName("TaskAlreadyMarkedException should be thrown")
    void noValidTaskIndexExceptionTesting() {
        Task newTask = new ToDo("Testing");
        Throwable exception = assertThrows(DukeException.class, () -> taskList.add(newTask).mark(0).mark(0));
        assertEquals("Oh hmm...seems like I've executed that already", exception.getMessage());
    }
}
