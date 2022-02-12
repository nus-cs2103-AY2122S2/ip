import chibot.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void stringStored_eventToBeStoredIntoFile_properlyFormatted() {
        Event toTest = new Event("some event", LocalDate.parse("2022-10-10"), LocalTime.parse("12:00"),
                LocalTime.parse("13:00"), false);
        assertEquals(" E | 0 | some event | 2022-10-10 | 12:00 | 13:00", toTest.convertToFileFormat());
    }
}