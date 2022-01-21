package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void stringToDate() {
        assertTrue(DateParser.parseDate("2021-11-11") instanceof LocalDate);
        assertEquals(DateParser.parseDate("2021-11-11").toString(), "2021-11-11");
    }

    @Test
    public void DateToString() {
        assertEquals(DateParser.dateToString(LocalDate.of(2017, 11, 6)), "6 November 2017");
        assertEquals(DateParser.dateToString(LocalDate.of(2090, 12, 5)), "5 December 2090");
    }
}
