package duke;

import org.junit.Test;

import java.util.ArrayList;

import static duke.Storage.taskList;
import static org.junit.Assert.assertEquals;

public class TaskListTest {
    @Test
    public void testAdd() {
        taskList = new ArrayList<>();
        TaskList.add(new ToDos("1234"));
        assertEquals(1, taskList.size());
    }

    @Test
    public void testDelete() {
        taskList = new ArrayList<>();
        TaskList.add(new ToDos("1234"));
        TaskList.delete(1);
        assertEquals(0, taskList.size());
    }
}
