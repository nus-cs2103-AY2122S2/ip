package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("task", false));
        assertEquals(tasks.size(), 1);
    }

    @Test
    public void markTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("task 1", false));
        tasks.addTask(new Todo("task 2", false));
        tasks.markTask(1);
        Task doneTask = tasks.getTask(1);
        assertEquals(true, doneTask.isDone);
    }
}
