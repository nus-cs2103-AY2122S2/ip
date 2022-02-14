package date.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.InvalidInputException;

/**
 * This class encapsulates tasks done on the DateTimeParser. In particular, these tests test on the
 * checkValidFormat method, as the parse and parseTime methods should only be called when there is
 * a correct format.
 *
 * @author Ong Han Yang
 */
public class DateTimeParserTest {
    /**
     * Tests the checkValidFormat method with a valid format.
     *
     * @throws InvalidInputException never.
     */
    @Test
    public void checkValidFormat_correctInput_success() throws InvalidInputException {
        assertTrue(DateTimeParser.checkValidFormat("1999-09-15 16:45"));
    }

    /**
     * Tests the checkValidFormat method with an input longer than 16 characters.
     */
    @Test
    public void checkValidFormat_longerThanExpected_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("1999-Sept-15 16:45"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("The date/time format is wrong! "
                    + "Please enter in this format: yyyy-mm-dd hh:mm", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with an input shorter than 16 characters.
     */
    @Test
    public void checkValidFormat_shorterThanExpected_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("1999-9-1 16:45"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("The date/time format is wrong! "
                    + "Please enter in this format: yyyy-mm-dd hh:mm", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with wrong separators
     */
    @Test
    public void checkValidFormat_wrongSeparators_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("1999.09.01 16-45"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("The date/time format is wrong! "
                    + "Please enter in this format: yyyy-mm-dd hh:mm", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with the time/date not as numbers.
     */
    @Test
    public void checkValidFormat_notNumbers_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("nine-09-01 16:45"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("The date/time format is wrong! "
                    + "Please enter in this format: yyyy-mm-dd hh:mm", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with a negative time/date. This is caught by the "not-numbers"
     * checks.
     */
    @Test
    public void checkValidFormat_negativeValue_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("1999-09-01 16:-1"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("The date/time format is wrong! "
                    + "Please enter in this format: yyyy-mm-dd hh:mm", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with an invalid minute.
     */
    @Test
    public void checkValidFormat_minute60_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("1999-09-01 16:60"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("There is no 60-th minute!", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with an invalid hour.
     */
    @Test
    public void checkValidFormat_hour24_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("1999-09-01 24:45"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("There is no 24-th hour!", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with an invalid day for the month.
     */
    @Test
    public void checkValidFormat_april31_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("2022-04-31 01:01"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("APRIL does not have a 31-th day!", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with an invalid day overall. (lower)
     */
    @Test
    public void checkValidFormat_day0_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("2022-04-00 01:01"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("There is no 0-th day!", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with an invalid day overall. (upper)
     */
    @Test
    public void checkValidFormat_day45_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("2022-04-45 01:01"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("There is no 45-th day!", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with Feb 29 on a leap year.
     */
    @Test
    public void checkValidFormat_feb29Leap_success() throws InvalidInputException {
        assertTrue(DateTimeParser.checkValidFormat("2020-02-29 01:01"));
    }

    /**
     * Tests the checkValidFormat method with Feb 29 not on a leap year.
     */
    @Test
    public void checkValidFormat_feb29NotLeap_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("2022-02-29 01:01"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("2022 is not a leap year! There is no Feb-29", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with an invalid month. (upper)
     */
    @Test
    public void checkValidFormat_month13_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("2022-13-15 01:01"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("There is no 13-th month!", e.getMessage());
        }
    }

    /**
     * Tests the checkValidFormat method with an invalid month. (lower)
     */
    @Test
    public void checkValidFormat_month0_exceptionThrown() {
        try {
            assertTrue(DateTimeParser.checkValidFormat("2022-00-15 01:01"));
            fail(); // should not reach here
        } catch (InvalidInputException e) {
            assertEquals("There is no 0-th month!", e.getMessage());
        }
    }
}
