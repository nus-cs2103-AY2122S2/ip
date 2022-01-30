package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
//        return "[D]" + super.toString() + " ( by:" + date.format(DateTimeFormatter.BASIC_ISO_DATE) + ")";

        assertEquals("[D][ ] read book (by: 20011109)", new Deadline("read book", LocalDate.of(2001, 11, 9)).toString());
    }

    @Test
    void toData() {
        assertEquals("D:0:read book:2001-11-09",
                new Deadline("read book", LocalDate.of(2001, 11, 9)).toData());
    }
}