package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    Task t = new Task("sleep");
    @Test
    void getStatusIcon() {
        Task t = new Task("sleep");
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    void getDescription() {
        assertEquals("sleep", t.getDescription());
    }

    @Test
    void markAsDone() {
        t.markAsDone();
        assertEquals("X", t.getStatusIcon());
    }

    @Test
    void markAsNotDone() {
        t.markAsNotDone();
        assertEquals(" ", t.getStatusIcon());
    }

    @Test
    void testToString() {
        assertEquals("[ ] added: sleep", t.toString());
    }
}