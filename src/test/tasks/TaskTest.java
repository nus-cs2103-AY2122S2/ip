package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for Tasks.
 */
class TaskTest {

    /**
     * Test method for setMarked in Tasks class.
     */
    @Test
    void testSetMarked() {
        Tasks t = new ToDos("hello", false);
        assertEquals(false, t.getMarked());
        t.setMarked(true);
        assertEquals(true, t.getMarked());
        t.setMarked(false);
        assertEquals(false, t.getMarked());
    }

    /**
     * Test method for getTask in Tasks class.
     */
    @Test
    void testGetTask() {
        Tasks t1 = new ToDos("hello world", false);
        assertEquals("hello world", t1.getTask());
        Tasks t2 = new ToDos("hello world #1e2d*asd", false);
        assertEquals("hello world #1e2d*asd", t2.getTask());
    }

    /**
     * Test method for returnDate in Tasks class.
     */
    @Test
    void testReturnDate() {
        assertEquals("12312312312412412", Tasks.returnDate("12312312312412412"));
        assertEquals("13-2020-15", Tasks.returnDate("13-2020-15"));
        assertEquals("Oct-2020-15", Tasks.returnDate("Oct-2020-15"));
        assertEquals("Jan 20 2015", Tasks.returnDate("2015-01-20"));
        assertEquals("Dec 10 1990", Tasks.returnDate("Dec 10 1990"));
    }
}
