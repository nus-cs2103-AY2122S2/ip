package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TaskStub;

public class TaskListTest {

    @Test
    public void addTest() {
        TaskList taskList = new TaskList();
        TaskStub taskStub = new TaskStub("Coding all day");
        taskList.add(taskStub);
        assertEquals(1, taskList.getCount());
    }

    @Test
    public void deleteTest() {
        TaskList taskList = new TaskList();
        taskList.delete(0);
        assertEquals(0, taskList.getCount());
    }

    @Test
    void getTaskTest() {
        TaskList taskList = new TaskList();
        TaskStub taskStub = new TaskStub("Coding all day");
        taskList.add(taskStub);
        assertEquals("Coding all day", taskList.getTask(0));
    }
}
