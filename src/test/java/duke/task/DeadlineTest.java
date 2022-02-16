package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_validDeadline_success() {
        Deadline d = new Deadline("test", LocalDate.parse("2022-12-10"));
        assertEquals("[D][ ] test (by: 10 Dec 2022)", d.toString());
    }
}
