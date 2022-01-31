import duke.common.Const;
import duke.task.Deadline;
import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testGetAtFormat() {
        LocalDateTime dateTime = LocalDateTime.parse("May 5 2022 23:59", Const.OUT_TIME_FORMATTER);
        Event event = new Event("party", dateTime);
        assertEquals("May 5 2022 23:59", event.getAt());
    }

    @Test
    public void testMarkStatus() {
        LocalDateTime dateTime = LocalDateTime.parse("May 5 2022 23:59", Const.OUT_TIME_FORMATTER);
        Event event = new Event("party", dateTime);
        event.mark();
        assertEquals("X", event.getStatusIcon());
        event.unMark();
        assertEquals(" ", event.getStatusIcon());
    }
}
