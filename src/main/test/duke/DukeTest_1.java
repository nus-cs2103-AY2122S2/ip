package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest_1 {

    @Test
    void checkMonthConverterMethod() {
        assertEquals("January",
                new Deadline("test").digitMonth_to_AlphabeticalMonth("01"));
        assertEquals("notValidMonth",
                new Deadline("test").digitMonth_to_AlphabeticalMonth("1"));
    }

    @Test
    void checkGetDescription() {
        //check for todo
        assertEquals("[T][ ] project", new Todo("todo project").getDescription());
        //check for deadline method
        assertEquals("[D][ ] assignment (by 12 December 1212, 630pm)",
                new Deadline("deadline assignment /by 12-12-1212 1830").getDescription());
        //check for event method
        assertEquals("[E][ ] party (at 11 November 1111, 520pm)",
                new Event("event party /at 11-11-1111 1720").getDescription());
    }

}