package duke.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;


public class ToDoTest {
    @Test
    void test_toStore() {
        assertEquals("T | 0 | name",
                new ToDo("name").toStore());
    }
    @Test
    void test_display() {
        assertEquals("[T] [ ] name",
                new ToDo("name").display());
    }
}
