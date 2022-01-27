package chatbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testToString() {
        ToDo task = new ToDo("My ToDo");
        assertEquals("[T][ ] My ToDo", task.toString());
    }

    @Test
    public void testSetDone() {
        ToDo task = new ToDo("My ToDo");
        task.setDone(true);
        assertEquals("[T][X] My ToDo", task.toString());
        task.setDone(false);
        assertEquals("[T][ ] My ToDo", task.toString());
    }
}
