package duke.task;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private static class TaskStub extends Task {
        private TaskStub(String description) {
            super(TaskType.TODO, description);
        }
    }

    @Test
    public void testAddRemoveGet_valid_success() {
        TaskList list = new TaskList();
        Task t1 = new TaskStub("Task 1");
        Task t2 = new TaskStub("Task 2");
        list.addTask(t1);
        assertEquals(1, list.getTaskCount());
        list.addTask(t2);
        assertEquals(2, list.getTaskCount());

        assertEquals(t1, list.getTaskByIndex(0));
        assertEquals(t2, list.getTaskByIndex(1));

        assertEquals(t1, list.deleteTask(0));
        assertEquals(1, list.getTaskCount());
        assertEquals(t2, list.getTaskByIndex(0));
    }

    @Test
    public void testRemoveGet_outOfBounds_null() {
        TaskList list = new TaskList();

        assertNull(list.getTaskByIndex(0));
        list.addTask(new TaskStub("Task 1"));
        assertNull(list.getTaskByIndex(-1));
        assertNotNull(list.getTaskByIndex(0));
        assertNull(list.getTaskByIndex(1));

        assertNull(list.deleteTask(-1));
        assertNull(list.deleteTask(1));
        assertNotNull(list.deleteTask(0));
    }

    @Test
    public void testMark_valid_success() {
        TaskList list = new TaskList();
        Task t1 = new TaskStub("Task 1");

        list.markTask(t1, true);
        assertTrue(t1.isDone());
        list.markTask(t1, true);
        assertTrue(t1.isDone());
        list.markTask(t1, false);
        assertFalse(t1.isDone());
        list.markTask(t1, false);
        assertFalse(t1.isDone());
    }

    @Test
    public void testForEach() {
        TaskList list = new TaskList();
        final HashMap<Integer, Task> tasks = new HashMap<>();

        Task[] sourceTasks = new Task[] {
                new TaskStub("Task"),
                new TaskStub("Task"),
                new TaskStub("Task"),
                new TaskStub("Task")
        };
        Arrays.stream(sourceTasks).forEachOrdered(list::addTask);

        list.doForEach(tasks::put);

        assertEquals(4, tasks.size());
        assertTrue(tasks.containsKey(0) && tasks.containsKey(1) && tasks.containsKey(2)
                && tasks.containsKey(3));
        assertEquals(sourceTasks[0], tasks.get(0));
        assertEquals(sourceTasks[1], tasks.get(1));
        assertEquals(sourceTasks[2], tasks.get(2));
        assertEquals(sourceTasks[3], tasks.get(3));
    }
}
