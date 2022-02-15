package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import karen.task.ToDo;

public class ToDoTest {
    protected static ToDo testTODO_;
    protected static ToDo testMarkTODO_;

    @BeforeAll
    public static void setUp() {
        testTODO_ = new ToDo("example");
        testMarkTODO_ = new ToDo("exampleMark");
        testMarkTODO_.markDone();
    }

    @Test
    public void toSaveData_success() {
        assertEquals(
                testTODO_.toSaveData(), "T|false|example"
        );
        assertEquals(
                testMarkTODO_.toSaveData(), "T|true|exampleMark"
        );
    }

    @Test
    public void toString_success() {
        assertEquals(
                testTODO_.toString(), "[T][ ] example"
        );
        assertEquals(
                testMarkTODO_.toString(), "[T][X] exampleMark"
        );
    }

    @AfterAll
    public static void tearDown() {
        testTODO_ = null;
        testMarkTODO_ = null;
    }
}
