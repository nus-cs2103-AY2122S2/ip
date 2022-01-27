import myTasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void toStringTest() {
        Event e = new Event("help mom", "2022-10-10 10PM");
        assertEquals("[E][ ] help mom (at: October 10, 2022 10PM)", e.toString());
    }
}