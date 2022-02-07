package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Represents test for the <code>DateParser</code> class
 */
public class DateParserTest {

    /**
     * Tests the parsing of strings into <code>LocalDate</code> objects
     */
    @Test
    public void stringToDate() {
        assertTrue(DateParser.parseDate("2021-11-11") instanceof LocalDate);
        assertEquals(DateParser.parseDate("2021-11-11").toString(), "2021-11-11");
    }

    /**
     * Tests the parsing of <code>LocalDate</code> objects into strings
     */
    @Test
    public void dateToString() {
        assertEquals(DateParser.dateToString(LocalDate.of(2017, 11, 6)), "6 November 2017");
        assertEquals(DateParser.dateToString(LocalDate.of(2090, 12, 5)), "5 December 2090");
    }
}
