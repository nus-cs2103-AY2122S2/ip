package baron.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import baron.exceptions.BaronException;

public class DateTimeUtilTest {
    @Test
    public void getDateTime_validString_success() throws BaronException {
        assertEquals(LocalDateTime.of(2022, 12, 1, 0, 11),
                DateTimeUtil.getDateTime("1/12/2022 00:11"));
    }

    @Test
    public void getDateTime_validStringWithPadding_success() throws BaronException {
        assertEquals(LocalDateTime.of(2022, 2, 1, 0, 11),
                DateTimeUtil.getDateTime("01/02/2022 00:11"));
    }

    @Test
    public void getDateTime_invalidString_exceptionThrown() {
        try {
            assertEquals(0, DateTimeUtil.getDateTime("invalid"));
            fail();
        } catch (BaronException e) {
            assertEquals("☹ OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm", e.toString());
        }
    }

    @Test
    public void getDateTime_onlyDateString_exceptionThrown() {
        try {
            assertEquals(0, DateTimeUtil.getDateTime("1/12/2022"));
            fail();
        } catch (BaronException e) {
            assertEquals("☹ OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm", e.toString());
        }
    }

    @Test
    public void getDateTime_onlyTimeString_exceptionThrown() {
        try {
            assertEquals(0, DateTimeUtil.getDateTime("00:11"));
            fail();
        } catch (BaronException e) {
            assertEquals("☹ OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm", e.toString());
        }
    }

    @Test
    public void getSaveString_validLocalDateTime_success() {
        assertEquals("12/2/2011 01:02", DateTimeUtil.getSaveString(LocalDateTime.of(2011, 2, 12, 1, 2)));
    }

    @Test
    public void getDisplayString_validLocalDateTime_success() {
        assertEquals("Feb 12 2011, 01:02", DateTimeUtil.getDisplayString(LocalDateTime.of(2011, 2, 12, 1, 2)));
    }
}
