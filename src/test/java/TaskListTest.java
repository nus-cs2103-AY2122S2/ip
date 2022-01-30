import duke.task.TaskStub;
import duke.tasklist.TaskList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void taskCanBeAdded_newTask_newTaskExistsInList() {
        TaskList t = new TaskList();
        t.addTask(new TaskStub(""));
        assertEquals("forT1", t.getTask(0).toString());
    }

    @Test
    void taskCanBeDeleted_newTask_taskListIsEmpty() {
        TaskList t = new TaskList();
        TaskStub ts = new TaskStub("");
        t.addTask(ts);
        t.deleteTask(ts);
        assertEquals(0, t.getSize());
    }

}