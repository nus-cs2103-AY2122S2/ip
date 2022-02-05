package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Event;

public class EventTest {
    @Test
    public void eventTest1() {
        Event event = new Event("test1", false, "E", "2022-12-01 12:20");
        assertEquals("[E][ ] test1 (at: Dec 1 2022, 12:20)", event.toString());
    }

    @Test
    public void eventTest2() {
        Event event = new Event("test2", true, "E", "2022-12-01 12:20");
        assertEquals("[E][X] test2 (at: Dec 1 2022, 12:20)", event.toString());
    }
}
