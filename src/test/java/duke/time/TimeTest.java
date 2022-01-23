package duke.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void testStringConversion() {
        assertEquals("23 Jan 2022", Time.convertToString(LocalDate.of(2022, 1, 23)));
    }

    @Test
    public void testDateConversion() {
        assertEquals(LocalDate.of(2021, 12, 25), Time.convertToDate("25 Dec 2021"));
    }
}
