package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void getDetails_newDeadlineCreated_returnStringArray() {
        ToDo todo = new ToDo("Description");
        String[] expected = new String[]{"TODO", "0", "Description", ""};
        String[] actual = todo.getDetails();
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
        assertEquals(expected[3], actual[3]);
    }
}
