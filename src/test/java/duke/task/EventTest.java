package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class EventTest {

    @Test
    public void eventTestString() {
        LocalDate date = LocalDate.parse("2022-01-01");
        Event event = new Event("New Year party", date);

        assertEquals("[E][ ] New Year party (at: Jan 1 2022)", event.toString());
    }
}
