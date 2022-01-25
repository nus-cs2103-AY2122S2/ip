import duke.data.task.EventTask;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTaskTest {
    @Test
    public void eventTask_constructor() {
        String eventString = "[E][ ] Chicken party (by: Oct 15 2019)";
        EventTask task = new EventTask("Chicken party", "2019-10-15");

        // correctly prints description, formatted date and completion status
        assertEquals(task.toString(), eventString);

        // invalid date format throws exception
        assertThrows(DateTimeParseException.class, () -> {
            new EventTask("Chicken party", "15-10-2022");
        });
    }
}
