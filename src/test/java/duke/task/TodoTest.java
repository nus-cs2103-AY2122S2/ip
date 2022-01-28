package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testToString_success() {
        Todo task = new Todo("x");
        assertEquals("[T][ ] x", task.toString());
    }

    @Test
    public void testGetStatusIcon_unmarked() {
        Todo task = new Todo("x");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void testGetStatusIcon_marked() {
        Todo task = new Todo("x");
        task.setStatus(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testGetDescription() {
        Todo task = new Todo("x");
        assertEquals("x", task.getDescription());
    }

    @Test
    public void testSetStatus_true() {
        Todo task = new Todo("x");
        task.setStatus(true);
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testSetStatus_false() {
        Todo task = new Todo("x");
        task.setStatus(false);
        assertEquals(" ", task.getStatusIcon());
    }
}
