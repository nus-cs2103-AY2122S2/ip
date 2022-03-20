package pac.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toString_validInput_validString() {
        assertEquals("[E][ ] get a haircut (at: Mar 15 2022)",
                new Event("get a haircut", "2022-03-15").toString());
    }

    @Test
    public void toWrite_validInput_validString() {
        assertEquals("E~1~get a haircut~2022-03-15" + System.lineSeparator(),
                new Event("get a haircut", "2022-03-15").markAsDone().toWrite());
    }
}
