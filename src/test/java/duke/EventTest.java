package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventTest_newEvent_success(){
        assertEquals("[E][ ] Attend Lecture (at: 5th Feb 4-5pm)",
                new Event("Attend Lecture", "5th Feb 4-5pm").toString());
    }
}
