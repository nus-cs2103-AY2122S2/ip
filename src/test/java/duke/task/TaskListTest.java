package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTodoTask_validTask_success() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.add(new ToDo("test"));
        assertEquals(1, taskList.getSize());
        assertEquals("test", taskList.getByIndex(0).getTask());
    }

    @Test
    public void removeTodoTask_validTask_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("todo"));
        TaskList taskList = new TaskList(tasks);
        taskList.remove(0);
        assertEquals(0, taskList.getSize());
    }
}
