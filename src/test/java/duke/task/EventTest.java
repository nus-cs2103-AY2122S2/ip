package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toString_event_correctStringRepresentationReturned() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse("01-01-2022 00:00", formatter);

        Event event = new Event("test", true, localDateTime);

        assertEquals(event.toString(), "[E][X] test (at: 00:00, Jan 01 2022)");
    }

    @Test
    public void toData_deadline_correctDataRepresentationReturned() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse("01-01-2022 00:00", formatter);

        Event event = new Event("test", true, localDateTime);

        assertEquals(event.toData(), "E | true | test | 2022-01-01T00:00");
    }
}
