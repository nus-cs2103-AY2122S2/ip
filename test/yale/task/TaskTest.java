package yale.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void getStatusIcon_isMarked() {
        Task task = new Task("work", true);
        assertEquals("[X]", task.getStatusIcon());
    }

    @Test
    void getStatusIcon_isUnmarked() {
        Task task = new Task("work", false);
        assertEquals("[ ]", task.getStatusIcon());
    }

    @Test
    void markTask() {
        Task task = new Task("work", false);
        task.markTask();
        assertEquals(true, task.isMarked);
    }

    @Test
    void unmarkTask() {
        Task task = new Task("work", false);
        task.unmarkTask();
        assertEquals(false, task.isMarked);
    }

    @Test
    void export() {
        Task task = new Task("work", false);
        assertEquals(" | 0 | work", task.export());
    }

    @Test
    void testToString() {
        Task task = new Task("work", true);
        assertEquals("[X] work", task.toString());
    }
}