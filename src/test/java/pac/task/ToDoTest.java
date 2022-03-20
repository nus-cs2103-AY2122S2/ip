package pac.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toString_validInput_validString() {
        assertEquals("[T][ ] get a haircut", new ToDo("get a haircut").toString());
    }

    @Test
    public void toWrite_validInput_validString() {
        assertEquals("T~1~get a haircut" + System.lineSeparator(),
                new ToDo("get a haircut").markAsDone().toWrite());
    }
}
