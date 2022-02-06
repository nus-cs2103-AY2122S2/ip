package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class EventTest {
    private Event event;

    @Test
    public void testCorrectEventString() {
        String eventExpected = "[E][ ] Eat apple (at: 02-05-2021)";
        String eventActual = "";

        try {
            event = new Event("Eat apple", "02-05-2021");
            eventActual = event.toString();
        } catch (DukeException duke) {
            Assertions.fail();
        }
        assertEquals(eventActual, eventExpected);
    }


    @Test
    public void testWrongEventString() {
        String eventExpected = "The date format parsed is incorrect!It should be dd-MM-yyyy or dd-MM-yyyy HH:mm!";
        String eventActual = "";

        try {
            event = new Event("Eat apple", "02/05/2021");
        } catch (DukeException duke) {
            eventActual = duke.toString();
        } finally {
            assertEquals(eventActual, eventExpected);
        }
    }

}
