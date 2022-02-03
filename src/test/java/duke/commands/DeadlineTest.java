package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline deadline = new Deadline("do homework", "20-12-2001 1322");

    @Test
    public void getDate_exampleDate_formattedDateReturned() {
        String d = "Dec 20 2001";
        assertEquals(d, deadline.getDate());
    }

    @Test
    public void getTime_exampleTime_formattedTimeReturned() {
        String t = "13:22 pm";
        assertEquals(t, deadline.getTime());
    }

    @Test
    public void toString_exampleDeadline_formattedDeadlineReturned() {
        String s = "[D][ ] do homework (by: Dec 20 2001 13:22 pm)";
        assertEquals(s, deadline.toString());
    }

}
