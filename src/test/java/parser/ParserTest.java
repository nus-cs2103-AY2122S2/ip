package parser;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void test_deadlineEmptyDate_exceptionThrown() {
        try {
            Parser.parse("deadline read a book /by ");
        } catch (DukeException e) {
            assertEquals("date or time was not specified! Try again.", e.getMessage());
        }
    }

    @Test
    public void test_eventEmptyLocation_exceptionThrown() {
        try {
            Parser.parse("event attend 2103 meeting /at ");
        } catch (DukeException e) {
            assertEquals("location was not specified! Try again.", e.getMessage());
        }
    }
}
