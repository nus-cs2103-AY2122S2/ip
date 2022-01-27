package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testSetMarked() {
        Task t = new ToDos("hello", false);
        assertEquals(false, t.getMarked());
        t.setMarked(true);
        assertEquals(true, t.getMarked());
        t.setMarked(false);
        assertEquals(false, t.getMarked());
    }

    @Test
    void testGetTask() {
        Task t1 = new ToDos("hello world", false);
        assertEquals("hello world", t1.getTask());
        Task t2 = new ToDos("hello world #1e2d*asd", false);
        assertEquals("hello world #1e2d*asd", t2.getTask());
    }

    @Test
    void testReturnDate() {
       assertEquals("12312312312412412", Task.returnDate("12312312312412412"));
       assertEquals("13-2020-15", Task.returnDate("13-2020-15"));
       assertEquals("Oct-2020-15", Task.returnDate("Oct-2020-15"));
       assertEquals("Jan 20 2015", Task.returnDate("2015-01-20"));
       assertEquals("Dec 10 1990", Task.returnDate("Dec 10 1990"));
    }
}