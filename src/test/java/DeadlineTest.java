import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dukeclasses.Deadline;
import java.time.LocalDate;



public class DeadlineTest {
    @Test
    public void testEventIdentify() {
        assertEquals("[D][ ] do homework (by: Oct 15 2011)",
            new Deadline("do homework", LocalDate.parse("2011-10-15")).identify());
    }

    @Test
    public void testSetIsDone() {
        Deadline deadline = new Deadline("do homework", LocalDate.parse("2011-10-15"));
        deadline.setDone(true);
        assertEquals("[D][X] do homework (by: Oct 15 2011)", deadline.identify());
        deadline.setDone(false);
        assertEquals("[D][ ] do homework (by: Oct 15 2011)", deadline.identify());
    }

    @Test
    public void testGetDescription() {
        assertEquals(" ", new Deadline(" ", LocalDate.parse("2011-10-15")).getDescription());
    }

    @Test
    public void testGetIsDone() {
        Deadline deadline = new Deadline("do homework", LocalDate.parse("2011-10-15"));
        assertEquals(false, deadline.getIsDone());
        deadline.setDone(true);
        assertEquals(true, deadline.getIsDone());
        deadline.setDone(false);
        assertEquals(false, deadline.getIsDone());
    }

}
