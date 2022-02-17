package tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
// import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void toStringTest() {
        Deadline todoTask = new Deadline(
            "test",
            LocalDate.parse("2021-01-01"),
            LocalTime.parse("18:00"));
        String actual = todoTask.toString();
        String expected = "[D][ ] test (at: Jan 01 2021 18:00:00)";
        assertEquals(expected, actual);
    }
}