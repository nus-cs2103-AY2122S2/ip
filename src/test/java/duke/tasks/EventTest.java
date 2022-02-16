package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    @DisplayName("Event string format is correct.")
    public void eventStringFormat() {
        String description = "read book";
        LocalDate date = LocalDate.parse("2022-01-30");
        Event testEvent = new Event(description, date);
        assertEquals("E | unmark | read book | 2022-01-30",
                testEvent.formatString());
    }

    @Test
    @DisplayName("Event string representation is correct.")
    public void eventToString() {
        String description = "sleep";
        LocalDate date = LocalDate.parse("2022-01-31");
        Event testEvent = new Event(description, date);
        assertEquals("[E][ ] sleep (at: Jan 31 2022)",
                testEvent.toString());
    }
}
