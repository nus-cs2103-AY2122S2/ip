package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void getDescription_sampleTodo_matchExpected() {
        ToDo todo = new ToDo("Test TODO");
        String expectedDescription = "[T][ ] Test TODO";
        assertEquals(expectedDescription, todo.getDescription());
    }

    @Test
    void encodeTaskData_sampleTodo_matchExpected() {
        ToDo todo = new ToDo("Test TODO");
        String expectedEncoding = "T,Test TODO,N,NA";
        assertEquals(expectedEncoding, todo.encodeTaskData());
    }
}
