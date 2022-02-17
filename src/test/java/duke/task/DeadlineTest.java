package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void dummyDeadline() {
        LocalDate date = LocalDate.parse("2022-01-21");
        Deadline deadline = new Deadline("Test Deadline", date);

        assertEquals("2022-01-21", deadline.getDate().toString());
    }

    @Test
    public void testToString() {
        LocalDate date = LocalDate.parse("2022-01-21");
        Deadline deadline = new Deadline("Test Deadline", date);

        assertEquals("[D][ ] Test Deadline (by: Jan 21 2022)", deadline.toString());
    }
}
