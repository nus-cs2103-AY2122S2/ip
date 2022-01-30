package tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void toStringTest() {
        Event todoTask = new Event(
            "test",
            LocalDate.parse("2021-01-01"),
            LocalTime.parse("18:00"));
        String actual = todoTask.toString();
        String expected = "[E][ ] test (at: Jan 01 2021 18:00:00)";
        assertEquals(expected, actual);
    }
}
