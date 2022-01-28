package duke;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void TodoTest1() {
        ToDo todo = new ToDo("test1", false, "T");
        assertEquals("[T][ ] test1" , todo.toString());
    }

    @Test
    public void TodoTest2() {
        ToDo todo = new ToDo("test1", true, "T");
        assertEquals("[T][X] test1" , todo.toString());
    }
}
