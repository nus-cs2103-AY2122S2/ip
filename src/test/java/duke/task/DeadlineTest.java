package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class DeadlineTest {

    @Test
    public void deadlineTestString() {
        LocalDate date = LocalDate.parse("2022-03-04");
        Deadline deadline = new Deadline("Return book", date);

        assertEquals("[D][ ] Return book (by: Mar 4 2022)", deadline.toString());
    }
}
