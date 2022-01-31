package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    @Test
    public void eventTask_valid_success() {
        assertTrue(new EventTask("Title 1", "2022-01-01") instanceof EventTask);
        assertTrue(new EventTask("Title 2", "2022-01-01", "11:11") instanceof EventTask);
        assertTrue(new EventTask("Title 3", true, "2022-01-01") instanceof EventTask);
        assertTrue(new EventTask("Title 4", true, "2022-01-01", "11:11") instanceof EventTask);
    }

    @Test
    public void eventTask_getEventTime_success() {
        assertEquals(new EventTask("Title 1", "2022-01-01").getEventTime(),
                "(at: 2022-01-01)");
        assertEquals(new EventTask("Title 2", "2022-01-01", "11:11").getEventTime(),
                "(at: 2022-01-01 11:11)");
    }

    @Test
    public void eventTask_toOutputLine_success() {
        assertEquals(new EventTask("Title 1", "2022-01-01").toOutputLine(),
                "E | 0 | Title 1 | 2022-01-01");
        assertEquals(new EventTask("Title 2", "2022-01-01", "11:11").toOutputLine(),
                "E | 0 | Title 2 | 2022-01-01 | 11:11");
        assertEquals(new EventTask("Title 3", true, "2022-01-01").toOutputLine(),
                "E | 1 | Title 3 | 2022-01-01");
        assertEquals(new EventTask("Title 4", true, "2022-01-01", "11:11").toOutputLine(),
                "E | 1 | Title 4 | 2022-01-01 | 11:11");
    }

    @Test
    public void eventTask_toString_success() {
        assertEquals(new EventTask("Title 1", "2022-01-01").toString(),
                "Title 1 (at: 2022-01-01)");
        assertEquals(new EventTask("Title 2", "2022-01-01", "11:11").toString(),
                "Title 2 (at: 2022-01-01 11:11)");
    }

}
