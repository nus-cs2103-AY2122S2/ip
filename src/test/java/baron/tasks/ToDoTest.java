package baron.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toString_basic_success() {
        assertEquals("[T][ ] todo test", new ToDo("todo test").toString());
    }

    @Test
    public void toString_marked_xInSecondBox() {
        ToDo toDo = new ToDo("todo test");
        assertTrue(toDo.mark());
        assertEquals("[T][X] todo test", toDo.toString());
    }

    @Test
    public void toSaveString_basic_success() {
        assertEquals("T|0|todo test", new ToDo("todo test").toSaveString("|"));
    }

    @Test
    public void toSaveString_marked_display1Not0() {
        ToDo toDo = new ToDo("todo test");
        assertTrue(toDo.mark());
        assertEquals("Tww1wwtodo test", toDo.toSaveString("ww"));
    }

    @Test
    public void getStatusIcon_unmark_space() {
        assertEquals(" ", new ToDo("todo test").getStatusIcon());
    }

    @Test
    public void getStatusIcon_mark_outputX() {
        ToDo toDo = new ToDo("todo test");
        assertTrue(toDo.mark());
        assertEquals("X", toDo.getStatusIcon());
    }

    @Test
    public void mark_basic_true() {
        ToDo toDo = new ToDo("todo test");
        assertTrue(toDo.mark());
    }

    @Test
    public void mark_markMarked_false() {
        ToDo toDo = new ToDo("todo test");
        assertTrue(toDo.mark());
        assertFalse(toDo.mark());
    }

    @Test
    public void unmark_basic_true() {
        ToDo toDo = new ToDo("todo test");
        assertTrue(toDo.mark());
        assertTrue(toDo.unmark());
    }

    @Test
    public void unmark_unmarkUnmarked_false() {
        ToDo toDo = new ToDo("todo test");
        assertFalse(toDo.unmark());
    }
}
