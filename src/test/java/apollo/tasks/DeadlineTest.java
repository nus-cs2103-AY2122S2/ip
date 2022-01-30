package apollo.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 1, 20, 0, 0);
        Task deadline = new Deadline("task", dateTime);
        String expected = "[D][ ] task (by: 20-01-2022 00:00)";
        assertEquals(expected, deadline.toString());
    }
}
