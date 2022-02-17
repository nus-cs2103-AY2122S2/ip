package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class EventTest {
    @Test
    public void testInstantiateEvent() {
        Event event = new Event("test", true, LocalDate.parse("2020-12-12"));
        assertEquals("[D][x] test (by: 2020-12-12", event.toString());
    }

}
