package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import exception.DukeException;

public class DeadlineTest {
    @Test
    public void conversionToDate_nonTime_success() {
        Deadline testDeadline = new Deadline("read a book", "2019-01-01");
        String testDateLine = testDeadline.formatDeadLine();
        assertEquals("Jan 01 2019", testDateLine);
    }

    @Test
    public void conversionToDate_withTime_success() {
        Deadline testDeadline = new Deadline("read a book", "2019-01-01 1800");
        String testDateLine = testDeadline.formatDeadLine();
        assertEquals("Jan 01 2019 1800", testDateLine);
    }

    @Test
    public void conversionToDate_nonTime_exceptionThrown() {
        try {
            Deadline testDeadline = new Deadline("read a book", "2019-44-44");
        } catch (DateTimeParseException e) {
            assertEquals("Text '2019-44-44' could not be parsed: Invalid "
                    + "value for MonthOfYear (valid values 1 - 12): 44", e.getMessage());
        }
    }

    @Test
    public void conversionToDate_withTime_exceptionThrown() throws DukeException {
        try {
            Deadline testDeadline = new Deadline("read a book", "2019-44-44 2500");
        } catch (DateTimeParseException e) {
            assertEquals("Text '2019-44-44 2500' could not be parsed: Invalid "
                    + "value for MonthOfYear (valid values 1 - 12): 44", e.getMessage());
        }
    }
}
