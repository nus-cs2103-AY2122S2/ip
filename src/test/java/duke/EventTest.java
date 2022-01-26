package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event e = new Event("Visiting", "at 6pm");
        assertEquals("[E][ ] Visiting (at 6pm)", e.toString());
    }

}
