package ari.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTest {

    private Task task = new Task("complete A-JUnit");

    @Test
    void testToString() {
        assertEquals("[ ] complete A-JUnit", task.toString());
    }

    @Test
    void writeToFile() {
        assertEquals("0 complete A-JUnit", task.writeToFile());
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
