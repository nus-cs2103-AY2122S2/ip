package siri;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    void saveDataTest() {
        ToDos todo = new ToDos("return book", 0);
        String expectedOuput = "T 0 return book";
        assertEquals(expectedOuput, todo.saveData());
    }
}
