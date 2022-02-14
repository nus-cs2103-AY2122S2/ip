package duke.functionality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void testInvalidCommandException() {
        try {
            assertEquals(0, Parser.parse("blah"));
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void testIncompleteCommandException() {
        try {
            assertEquals(0, Parser.parse("todo"));
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void dateToString_validDate_success() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate test1 = LocalDate.parse("2020-06-06", dtf);
        assertEquals("2020-06-06", Parser.dateToString(test1));

        LocalDate test2 = LocalDate.parse("2020-07-31", dtf);
        assertEquals("2020-07-31", Parser.dateToString(test2));
    }

    @Test
    public void timeToString_validTime_success() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
        LocalTime test1 = LocalTime.parse("1800", dtf);
        assertEquals("1800", Parser.timeToString(test1));

        LocalTime test2 = LocalTime.parse("2400", dtf);
        assertEquals("0000", Parser.timeToString(test2));
    }
}
