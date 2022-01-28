package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoToString_validInput_validOutput() {
        assertEquals("T|0|homework|\n", new Todo("homework").toString());
    }
    @Test
    public void todoPrintTask_validInput_validOutput() {
        assertEquals("[T][ ] exam",new Todo("exam").printTask());
    }
}
