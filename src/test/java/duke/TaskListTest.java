package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskListTest {
    @Test
    public void newTaskList() {
        TaskList tasks = new TaskList();
        assertEquals(tasks.size(), 0);
        ArrayList<Task> taskArray = new ArrayList<>();
        taskArray.add(new TaskStub());
        tasks = new TaskList(taskArray);

        assertEquals(tasks.size(), 1);
    }

    @Test
    public void addTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(new TaskStub());

        assertEquals(tasks.size(), 1);
    }
}
