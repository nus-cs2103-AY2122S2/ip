package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import echo.task.EventTask;

public class EventTaskTest {

    @Test
    public void testSaveFormat() {
        LocalDateTime localDateTime = LocalDateTime.parse("2022-2-4 0400",
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
        String desc = "project meeting";
        EventTask eventTask = new EventTask(desc, localDateTime);
        assertEquals("E | 0 | project meeting | 2022-2-4 0400", eventTask.saveFormat());
    }

    @Test
    public void testToString() {
        LocalDateTime localDateTime = LocalDateTime.parse("2022-2-4 0400",
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
        String desc = "project meeting";
        EventTask eventTask = new EventTask(desc, localDateTime);
        assertEquals("[E][ ] project meeting (at: Feb 04 2022, 4:00AM)", eventTask.toString());
    }

}
