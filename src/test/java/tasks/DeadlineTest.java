package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    public void clone_uncompletedDeadline_success() {
        final Deadline expected = new Deadline("Return Book", LocalDate.now());
        assertEqualDeadlines(expected, expected.clone());
    }

    @Test
    public void clone_completedDeadline_success() {
        final Deadline expected = new Deadline("Return Book", LocalDate.now());
        expected.isDone = true;
        assertEqualDeadlines(expected, expected.clone());
    }

    @Test
    public void toString_uncompletedDeadline_success() {
        final String description = "Return Book";
        final int year = 2022;
        final int month = 3;
        final int day = 1;

        final String expected = "[D][LOW][ ] " + description + " (by: Mar 01 2022)";
        final Deadline actual = new Deadline(description, LocalDate.of(year, month, day));

        assertEquals(expected, actual.toString());
    }

    @Test
    public void toString_completedDeadline_success() {
        final String description = "Return Book";
        final int year = 2022;
        final int month = 3;
        final int day = 1;

        final String expected = "[D][LOW][X] " + description + " (by: Mar 01 2022)";
        final Deadline actual = new Deadline(description, LocalDate.of(year, month, day));
        actual.isDone = true;

        assertEquals(expected, actual.toString());
    }

    private void assertEqualDeadlines(Deadline d1, Deadline d2) {
        assertEquals(d1.description, d2.description);
        assertEquals(d1.isDone, d2.isDone);
        assertEquals(d1.getBy(), d2.getBy());
        assertEquals(d1.priority, d2.priority);
    }
}
