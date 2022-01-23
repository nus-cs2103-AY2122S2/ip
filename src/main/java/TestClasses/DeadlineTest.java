package TestClasses;

import dukeClasses.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testEventIdentify(){
        assertEquals("[D][ ] do homework (by: Oct 15 2011)",
            new Deadline("do homework", LocalDate.parse("2011-10-15")).identify());
    }

    @Test
    public void testSetIsDone() {
        Deadline deadline = new Deadline("do homework", LocalDate.parse("2011-10-15"));
        deadline.setIsDone(true);
        assertEquals("[D][X] do homework (by: Oct 15 2011)", deadline.identify());
        deadline.setIsDone(false);
        assertEquals("[D][ ] do homework (by: Oct 15 2011)", deadline.identify());
    }

    @Test
    public void testGetDescription(){
        assertEquals(" ", new Deadline(" ", LocalDate.parse("2011-10-15")).getDescription());
    }

    @Test
    public void testGetIsDone(){
        Deadline deadline = new Deadline("do homework", LocalDate.parse("2011-10-15"));
        assertEquals(false, deadline.getIsDone());
        deadline.setIsDone(true);
        assertEquals(true, deadline.getIsDone());
        deadline.setIsDone(false);
        assertEquals(false, deadline.getIsDone());
    }

}
