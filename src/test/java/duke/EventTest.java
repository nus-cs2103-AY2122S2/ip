package duke;

import exceptions.DukeEventException;
import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toString_EventObject_Success() {
        assertEquals("[E][ ] lecture (at:LT13)", Event.setEvent("lecture /at LT13").toString());
    }

    @Test
    public void markDoneToString_EventObject_Success() {
        Event event = Event.setEvent("lecture /at LT13");
        assert event != null;
        event.markDone();
        assertEquals("[E][X] lecture (at:LT13)", event.toString());
    }
}
