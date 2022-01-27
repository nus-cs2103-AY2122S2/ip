package Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventsTest {

    @Test
    void cacheString() {
        //testcase 1
        Events e1 = new Events("hello", true, "Sunday");
        assertEquals("E|1|hello|Sunday", e1.cacheString());

        //testcase 2
        Events e2 = new Events("hello", false, "Sunday");
        assertEquals("E|0|hello|Sunday", e2.cacheString());

        //testcase 3
        Events e3 = new Events("hello", false, "2020-10-17");
        assertEquals("E|0|hello|Oct 17 2020", e3.cacheString());

        //testcase 4
        Events e4 = new Events("home work", true, "2020-10-17");
        assertEquals("E|1|home work|Oct 17 2020", e4.cacheString());
    }

    @Test
    void testToString() {
        //testcase 1
        Events e1 = new Events("hello", true, "Sunday");
        assertEquals("[E][X] hello (at: Sunday)", e1.toString());

        //testcase 2
        Events e2 = new Events("hello", false, "Sunday");
        assertEquals("[E][ ] hello (at: Sunday)", e2.toString());

        //testcase 3
        Events e3 = new Events("hello", false, "2020-10-17");
        assertEquals("[E][ ] hello (at: Oct 17 2020)", e3.toString());

        //testcase 4
        Events e4 = new Events("home work", true, "2020-10-17");
        assertEquals("[E][X] home work (at: Oct 17 2020)", e4.toString());
    }
}