package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getDescription() {
        TaskStub task = new TaskStub("taskTest", false);
        assertEquals("taskTest", task.getDescription());
    }

    @Test
    void markDone() {
        TaskStub task = new TaskStub("taskTest", false);
        task.markDone();
        assertEquals(true, task.isDone);
    }

    @Test
    void unmarkDone() {
        TaskStub task = new TaskStub("taskTest", true);
        task.unmarkDone();
        assertEquals(false, task.isDone);
    }

    @Test
    void testToString() {
    }
}