package aeromon.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toString_normal_success() {
        Deadline deadline = new Deadline("Outing", LocalDate.of(2022, 2, 14));
        assertEquals("[D][ ] Outing (by: Feb 14 2022)", deadline.toString());
    }

    @Test
    public void toString_marked_indicated() {
        Deadline deadline = new Deadline("Outing", LocalDate.of(2022, 2, 14));
        deadline.markAsDone();
        assertEquals("[D][X] Outing (by: Feb 14 2022)", deadline.toString());
    }

    @Test
    public void toOutputFormat_normal_success() {
        Deadline deadline = new Deadline("Outing", LocalDate.of(2022, 2, 14));
        assertEquals("D / 0 / Outing / Feb 14 2022", deadline.toOutputFormat());
    }

    @Test
    public void toOutputFormat_marked_indicated() {
        Deadline deadline = new Deadline("Outing", LocalDate.of(2022, 2, 14));
        deadline.markAsDone();
        assertEquals("D / 1 / Outing / Feb 14 2022", deadline.toOutputFormat());
    }
}
