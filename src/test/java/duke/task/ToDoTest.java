package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.ToDo;

public class ToDoTest {

    /**
     * Tests whether toString method is properly written
     */
    @Test
    public void stringTester() {
        ToDo testToDo = new ToDo("testing");

        assertEquals("[T][ ] testing", testToDo.toString());
    }
}
