package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoToString_validInput_validOutput() {
        assertEquals("T|false|homework|\n", new Todo("homework").toString());
    }
    @Test
    public void todoPrintTask_validInput_validOutput() {
        assertEquals("[T][ ] exam", new Todo("exam").printTask());
    }
}
