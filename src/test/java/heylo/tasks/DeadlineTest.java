package heylo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_validDeadline_success() {
        Deadline deadline = new Deadline("deadline-1", "2022-02-09");
        assertEquals(" [D][ ] deadline-1\t (by Feb 9 2022)", deadline.toString());
    }

    @Test
    public void toString_invalidDeadline_handleError() {
        Deadline deadline = new Deadline("deadline-1", "not-a-date");
        assertEquals(" [D][ ] deadline-1\t (by )", deadline.toString());
    }
}
