package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void parserDeadlineValidator_noByDate_exceptionThrown() {
       try {
           Parser.parserDeadlineValidator("deadline CS2103 Week 3 /by");

       } catch (DukeException e) {
            assertEquals("The description of a deadline cannot be empty.", e.getMessage());
       }
    }

    @Test
    public void eventTaskValidator_noAtDate_exceptionThrown() {
        try {
            Parser.eventTaskValidator("live singing");
        } catch (DukeException e) {
            assertEquals("Invalid at date for event task: live singing", e.getMessage());
        }
    }


}
