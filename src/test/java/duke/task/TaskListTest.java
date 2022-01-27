package duke.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    private static TaskList taskList = new TaskList();
    private static TaskList initialList = new TaskList();

    @BeforeEach
    public void setUpTaskList() {
        taskList.getTasks().clear();
        taskList.getTasks().add(new ToDo("Hello"));
        taskList.getTasks().add(new ToDo("World"));

        initialList.getTasks().clear();
        initialList.getTasks().add(new ToDo("Hello"));
        initialList.getTasks().add(new ToDo("World"));
    }

    @Test
    public void addTaskTest() {
        taskList.addTask(new ToDo("content"));
        initialList.getTasks().add(new ToDo("content"));
        assertEquals(taskList, initialList);
    }

    @Test
    public void deleteTaskTest() {
        taskList.getTasks().add(new ToDo("what"));
        taskList.deleteTask(3);
        assertEquals(taskList, initialList);
    }
}
