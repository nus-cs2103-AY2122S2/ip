package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for DeadLines.
 */
class DeadLinesTest {

    /**
     * Test method for cacheString in DeadLines class.
     */
    @Test
    void testCacheString() {
        //testcase 1
        DeadLines d1 = new DeadLines("hello", true, "Sunday");
        assertEquals("D|1|hello|Sunday", d1.cacheString());

        //testcase 2
        DeadLines d2 = new DeadLines("hello", false, "Sunday");
        assertEquals("D|0|hello|Sunday", d2.cacheString());

        //testcase 3
        DeadLines d3 = new DeadLines("hello", false, "2020-10-17");
        assertEquals("D|0|hello|Oct 17 2020", d3.cacheString());

        //testcase 4
        DeadLines d4 = new DeadLines("home work", true, "2020-10-17");
        assertEquals("D|1|home work|Oct 17 2020", d4.cacheString());
    }

    /**
     * Test method for toString in DeadLines class.
     */
    @Test
    void testToString() {
        //testcase 1
        DeadLines d1 = new DeadLines("hello", true, "Sunday");
        assertEquals("[D][X] hello (by: Sunday)", d1.toString());

        //testcase 2
        DeadLines d2 = new DeadLines("hello", false, "Sunday");
        assertEquals("[D][ ] hello (by: Sunday)", d2.toString());

        //testcase 3
        DeadLines d3 = new DeadLines("hello", false, "2020-10-17");
        assertEquals("[D][ ] hello (by: Oct 17 2020)", d3.toString());

        //testcase 4
        DeadLines d4 = new DeadLines("home work", true, "2020-10-17");
        assertEquals("[D][X] home work (by: Oct 17 2020)", d4.toString());
    }
}
