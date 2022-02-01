package siri;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    void saveDataTest() {
        ToDos todo = new ToDos("return book", false);
        String expectedOuput = "T false return book";
        assertEquals(expectedOuput, todo.saveData());
    }
}
