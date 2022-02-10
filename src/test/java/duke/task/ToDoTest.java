package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void stringTester() {
        ToDo testToDo = new ToDo("testing");

        assertEquals("[T][ ] testing", testToDo.toString());
    }
}
