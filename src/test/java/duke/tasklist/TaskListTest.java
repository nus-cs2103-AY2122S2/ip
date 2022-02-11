package duke.tasklist;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class TaskListTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void addTask_addTodo_expectedBehavior() {
        TaskList taskList = new TaskList();
        ToDo freshTodo = new ToDo("try this");
        taskList.addTask(freshTodo);
        assertEquals("[T][ ] try this", taskList.getTask(0).toString());
    }

    @Test
    public void markTask_markTodo_expectedBehavior() {
        TaskList taskList = new TaskList();
        ToDo freshTodo = new ToDo("try this");
        freshTodo.setDone(true);
        taskList.addTask(freshTodo);
        assertEquals("[T][X] try this", taskList.getTask(0).toString());
    }
}
