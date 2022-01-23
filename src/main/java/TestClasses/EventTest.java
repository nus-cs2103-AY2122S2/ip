package TestClasses;

import dukeClasses.Event;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEventIdentify(){
        assertEquals("[E][ ] do homework (by: Oct 15 2011)",
            new Event("do homework", LocalDate.parse("2011-10-15")).identify());
    }

    @Test
    public void testSetIsDone() {
        Event event = new Event("do homework", LocalDate.parse("2011-10-15"));
        event.setIsDone(true);
        assertEquals("[E][X] do homework (by: Oct 15 2011)", event.identify());
        event.setIsDone(false);
        assertEquals("[E][ ] do homework (by: Oct 15 2011)", event.identify());
    }

    @Test
    public void testGetDescription(){
        assertEquals(" ", new Event(" ", LocalDate.parse("2011-10-15")).getDescription());
    }

    @Test
    public void testGetIsDone(){
        Event event = new Event("do homework", LocalDate.parse("2011-10-15"));
        assertEquals(false, event.getIsDone());
        event.setIsDone(true);
        assertEquals(true, event.getIsDone());
        event.setIsDone(false);
        assertEquals(false, event.getIsDone());
    }


}

