package duke.duke.task;

import main.java.duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    void test_toStore() {
        assertEquals("T | 0 | name", new ToDo("name" ).toStore());
    }
    @Test
    void test_display() {
        assertEquals("[T] [ ] name", new ToDo("name").display());
    }
}
