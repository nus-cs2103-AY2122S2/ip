package mcbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void getStatusIcon_unmarked() {
        assertEquals(" ", new Task("taskName").getStatusIcon());
    }

    @Test
    void getStatusIcon_marked() {
        Task task = new Task("taskName");
        task.markDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    void getTaskIcon() {
        assertEquals(" ", new Task("taskName").getTaskIcon());
    }

    @Test
    void toDataString() {
        assertEquals("", new Task("taskName").toDataString());
    }

    @Test
    void testToString() {
        assertEquals("[ ][ ] taskName", new Task("taskName").toString());
    }
}