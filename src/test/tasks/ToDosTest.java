package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ToDos.
 */
class ToDosTest {

    /**
     * Test method for cacheString in ToDos class.
     */
    @Test
    void testCacheString() {
        ToDos t1 = new ToDos("hello", true);
        assertEquals("T|1|hello", t1.cacheString());

        //testcase 2
        ToDos t2 = new ToDos("hello", false);
        assertEquals("T|0|hello", t2.cacheString());

        //testcase 3
        ToDos t3 = new ToDos("hello", false);
        assertEquals("T|0|hello", t3.cacheString());

        //testcase 4
        ToDos t4 = new ToDos("home work", true);
        assertEquals("T|1|home work", t4.cacheString());
    }

    /**
     * Test method for toString in ToDos class.
     */
    @Test
    void testToString() {
        //testcase 1
        ToDos t1 = new ToDos("hello", true);
        assertEquals("[T][X] hello", t1.toString());

        //testcase 2
        ToDos t2 = new ToDos("hello", false);
        assertEquals("[T][ ] hello", t2.toString());

        //testcase 3
        ToDos t3 = new ToDos("hello", false);
        assertEquals("[T][ ] hello", t3.toString());

        //testcase 4
        ToDos t4 = new ToDos("home work", true);
        assertEquals("[T][X] home work", t4.toString());
    }
}