package dazz.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testWriteToFile_success() {
        Todo todo = new Todo("study");
        assertEquals("T === 0 === study === ", todo.writeToFile());
    }

    @Test
    public void testToString_success() {
        Todo todo = new Todo("study", true);
        assertEquals("[T][X] study", todo.toString());
    }

}
