import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dukeclasses.Event;

public class EventTest {

    @Test
    public void testEventIdentify() {
        assertEquals("[E][ ] do homework (on: Oct 15 2011)\n",
            new Event("do homework", LocalDate.parse("2011-10-15"), null).toString());
    }

    @Test
    public void testSetIsDone() {
        Event event = new Event("do homework", LocalDate.parse("2011-10-15"), null);
        event.setDone(true);
        assertEquals("[E][X] do homework (on: Oct 15 2011)\n", event.toString());
        event.setDone(false);
        assertEquals("[E][ ] do homework (on: Oct 15 2011)\n", event.toString());
    }

    @Test
    public void testGetDescription() {
        assertEquals(" ", new Event(" ", LocalDate.parse("2011-10-15"), null).getDescription());
    }

    @Test
    public void testGetIsDone() {
        Event event = new Event("do homework", LocalDate.parse("2011-10-15"), null);
        assertEquals(false, event.getIsDone());
        event.setDone(true);
        assertEquals(true, event.getIsDone());
        event.setDone(false);
        assertEquals(false, event.getIsDone());
    }

}

