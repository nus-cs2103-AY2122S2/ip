import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dukeclasses.Deadline;

public class DeadlineTest {
    @Test
    public void testEventIdentify() {
        assertEquals("[D][ ] do homework (by: Oct 15 2011)\n",
            new Deadline("do homework", LocalDate.parse("2011-10-15"), null).toString());
    }

    @Test
    public void testSetIsDone() {
        Deadline deadline = new Deadline("do homework", LocalDate.parse("2011-10-15"), null);
        deadline.setDone(true);
        assertEquals("[D][X] do homework (by: Oct 15 2011)\n", deadline.toString());
        deadline.setDone(false);
        assertEquals("[D][ ] do homework (by: Oct 15 2011)\n", deadline.toString());
    }

    @Test
    public void testGetDescription() {
        assertEquals(" ", new Deadline(" ", LocalDate.parse("2011-10-15"), null).getDescription());
    }

    @Test
    public void testGetIsDone() {
        Deadline deadline = new Deadline("do homework", LocalDate.parse("2011-10-15"), null);
        assertEquals(false, deadline.getIsDone());
        deadline.setDone(true);
        assertEquals(true, deadline.getIsDone());
        deadline.setDone(false);
        assertEquals(false, deadline.getIsDone());
    }

}
