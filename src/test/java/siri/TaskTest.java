package siri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void saveDataTest() {
        Task todo = new ToDos("return book", false);
        String expectedOuput = "T false return book";
        assertEquals(expectedOuput, todo.saveData());
    }
}
