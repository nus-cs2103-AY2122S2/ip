package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void printTaskCount_emptyList() {
        TaskList t = new TaskList(new ArrayList<Task>());
        assertEquals("Now you have 0 task(s) in the list.", t.printTaskCount());
    }

    @Test
    public void PrintTaskCount_nonEmptyList() {
        TaskList t = new TaskList(new ArrayList<Task>());
        t.addTask(new Task("Test"));
        assertEquals("Now you have 1 task(s) in the list.", t.printTaskCount());
    }
}
