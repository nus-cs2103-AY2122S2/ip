package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testGetStatusIcon() {
        assertEquals("X", new Task("testing task", true).getStatusIcon());
    }

    @Test
    void testMarkAsDone() {
        Task task = new Task("testing mark as done", false);
        task.markAsDone();
        assertEquals(true, task.isDone);
    }

    @Test
    void testMarkAsUndone() {
        Task task = new Task("testing mark as undone", true);
        task.markAsUndone();
        assertEquals(false, task.isDone);
    }

    @Test
    void testGetBool() {
        assertEquals("1", new Task("test getBool1", true).getBool());
        assertEquals("0", new Task("test getBool0", false).getBool());
    }

    @Test
    void testGetLetter() {
        assertEquals("T", new Todo("todo").getLetter());
        assertEquals("E", new Event("event", "at here").getLetter());
        assertNull(new Task("task").getLetter());
    }


    @Test
    void testToString() {
    assertEquals("[X] task description", new Task("task description", true).toString());
    }
}