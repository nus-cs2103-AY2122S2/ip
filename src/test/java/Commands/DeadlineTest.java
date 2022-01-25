package Commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    public void setUpDeadline() {
        this.deadline = new Deadline("do homework", "20-12-2001 1322");
    }

    @Test
    public void testGetDate() {
        String d = "Dec 20 2001";
        assertEquals(d, deadline.getDate());
    }

    @Test
    public void testGetTime() {
        String t = "13:22 pm";
        assertEquals(t, deadline.getTime());
    }

    @Test
    public void testToString() {
        String s = "[D][ ] do homework (by: Dec 20 2001 13:22 pm)";
        assertEquals(s, deadline.toString());
    }

}
