package task;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Deadline;

import java.io.Reader;
import java.io.StringReader;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void conversionToDate_nonTime_success() {
        Deadline testDeadline = new Deadline("read a book", "2019-01-01");
        String testDateLine = testDeadline.formatDeadline();
        assertEquals("Jan 01 2019", testDateLine);
    }

    @Test
    public void conversionToDate_Time_success() {
        Deadline testDeadline = new Deadline("read a book", "2019-01-01 1800");
        String testDateLine = testDeadline.formatDeadline();
        assertEquals("Jan 01 2019 1800", testDateLine);
    }

    @Test
    public void conversionToDate_nonTime_exceptionThrown() {
        try {
            Deadline testDeadline = new Deadline("read a book", "2019-44-44");
        } catch (DateTimeParseException e) {
            assertEquals("Text '2019-44-44' could not be parsed: Invalid " +
                    "value for MonthOfYear (valid values 1 - 12): 44", e.getMessage());
        }
    }

    @Test
    public void conversionToDate_Time_exceptionThrown() throws DukeException {
        try {
            Deadline testDeadline = new Deadline("read a book", "2019-44-44 2500");
        } catch (DateTimeParseException e) {
            assertEquals("Text '2019-44-44 2500' could not be parsed: Invalid " +
                    "value for MonthOfYear (valid values 1 - 12): 44", e.getMessage());
        }
    }
}
