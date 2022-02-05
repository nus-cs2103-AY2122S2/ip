package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDo;


public class ToDoTest {
    @Test
    public void todoTest1() {
        ToDo todo = new ToDo("test1", false, "T");
        assertEquals("[T][ ] test1" , todo.toString());
    }

    @Test
    public void todoTest2() {
        ToDo todo = new ToDo("test1", true, "T");
        assertEquals("[T][X] test1" , todo.toString());
    }
}
