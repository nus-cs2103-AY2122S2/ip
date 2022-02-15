package mcbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void getTaskIcon() {
        assertEquals("T", new ToDo("task").getTaskIcon());
    }

    @Test
    void toDataString_unmarked() {
        assertEquals("T | 0 | name", new ToDo("name").toDataString());
    }

    @Test
    void toDataString_marked() {
        ToDo task = new ToDo("name");
        task.markDone();
        assertEquals("T | 1 | name", task.toDataString());
    }

    @Test
    void testToString() {
        assertEquals("[T][ ] name", new ToDo("name").toString());
    }
}