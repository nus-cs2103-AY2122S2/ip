package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeCustomFormatterTest {
    @Test
    public void dateToString_LocalDateTime_success() {
        LocalDateTime input = LocalDateTime.of(2022, 4, 3, 18, 0);
        String expected = " 03/04/2022 1800";
        assertEquals(expected, DateTimeCustomFormatter.getStringFromDate(input));
    }

    @Test
    public void stringToDate_String_success() {
        String input = " 03/04/2022 1800";
        LocalDateTime expected = LocalDateTime.of(2022, 4, 3, 18, 0);
        assertEquals(expected, DateTimeCustomFormatter.getDateFromString(input));
    }
}
