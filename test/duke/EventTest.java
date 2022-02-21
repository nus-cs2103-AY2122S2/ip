package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        assertEquals("[E][ ] read book (at: 20011109)",
                new Event("read book", LocalDate.of(2001, 11, 9)).toString());
    }

    @Test
    void toData() {
        assertEquals("E:0:read book:2001-11-09",
                new Event("read book", LocalDate.of(2001, 11, 9)).toData());
    }
}