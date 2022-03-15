package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("CS1234 Lab", LocalDate.parse("1999-01-01"));
        assertEquals("[D][ ] CS1234 Lab (by: Jan 01 1999)", deadline.toString());
    }
}
