package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;

// Important note: For the test cases, there's no white spacing because the text hasn't been formatted yet.
// So the expected output is the raw output from Deadline.java
public class DeadlineTest {
    @Test
    public void testStringCmd() {
        Deadline testItem = new Deadline("deadlineTest", false, "deadlineTestDate");
        assertEquals("[ ]&D&deadlineTest&deadlineTestDate", testItem.getStringCmd(),
                "getStringCmd() works as intended");
    }

    @Test
    public void testGetDeadline() {
        Deadline testItem = new Deadline("deadlineTest", false, "deadlineTestDate");
        assertEquals("(by:deadlineTestDate)", testItem.getDeadline(),
                "getDeadline() works as intended");
    }

    @Test
    public void testToString() {
        Deadline testItem = new Deadline("deadlineTest", false, "deadlineTestDate");
        assertEquals("[D][ ]deadlineTest(by:deadlineTestDate)", testItem.toString(),
                "toString() works as intended");
    }
}
