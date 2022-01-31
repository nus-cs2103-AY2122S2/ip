import dukeclasses.Event;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEventIdentify() {
        assertEquals("[E][ ] do homework (by: Oct 15 2011)",
            new Event("do homework", LocalDate.parse("2011-10-15")).identify());
    }

    @Test
    public void testSetIsDone() {
        Event event = new Event("do homework", LocalDate.parse("2011-10-15"));
        event.setDone(true);
        assertEquals("[E][X] do homework (by: Oct 15 2011)", event.identify());
        event.setDone(false);
        assertEquals("[E][ ] do homework (by: Oct 15 2011)", event.identify());
    }

    @Test
    public void testGetDescription() {
        assertEquals(" ", new Event(" ", LocalDate.parse("2011-10-15")).getDescription());
    }

    @Test
    public void testGetIsDone() {
        Event event = new Event("do homework", LocalDate.parse("2011-10-15"));
        assertEquals(false, event.getIsDone());
        event.setDone(true);
        assertEquals(true, event.getIsDone());
        event.setDone(false);
        assertEquals(false, event.getIsDone());
    }

}

