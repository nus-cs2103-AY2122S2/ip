package apollo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 20, 0, 0);
        Task event = new Event("task", dateTime);
        String expected = "[E][ ] task (at: 20-01-2022 00:00)";
        assertEquals(expected, event.toString());
    }
}
