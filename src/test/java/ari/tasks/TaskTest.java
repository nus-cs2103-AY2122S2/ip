package ari.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task = new Task("complete A-Junit");

    @Test
    void testToString() {
        assertEquals("[ ] complete A-JUnit", task.toString());
    }

    @Test
    void writeToFile() {
        assertEquals("0 complete A-Junit", task.writeToFile());
    }

    @Test
    void hasDone() {
        assertEquals(false, task.hasDone());
    }

    @Test
    void markDone() {
        task.markDone();
        assertEquals(true, task.hasDone());
    }

    @Test
    void markNotDone() {
        task.markNotDone();
        assertEquals(false, task.hasDone());
    }

    @Test
    void getDescription() {
        assertEquals("complete A-JUnit", task.getDescription());
    }
}