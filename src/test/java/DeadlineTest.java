import duke.common.Const;
import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void testGetByFormat() {
        LocalDateTime dateTime = LocalDateTime.parse("May 5 2022 23:59", Const.OUT_TIME_FORMATTER);
        Deadline deadline = new Deadline("homework", dateTime);
        assertEquals("May 5 2022 23:59", deadline.getBy());
    }

    @Test
    public void testMarkStatus() {
        LocalDateTime dateTime = LocalDateTime.parse("May 5 2022 23:59", Const.OUT_TIME_FORMATTER);
        Deadline deadline = new Deadline("homework", dateTime);
        deadline.mark();
        assertEquals("X", deadline.getStatusIcon());
        deadline.unMark();
        assertEquals(" ", deadline.getStatusIcon());
    }
}
