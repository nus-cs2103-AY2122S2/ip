import duke.tasks.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {

    private LocalDateTime date;
    private Event event;

    @BeforeEach
    public void setUp(){
        date = LocalDateTime.parse("2007-12-03T10:15:30");
        event = new Event("Event Name", date);
    }

    @Test
    public void getType_noInputs_success() {
        assertEquals('E', event.getType());
    }

    @Test
    public void toString_noInputs_success() {
        assertEquals("[E][ ] Event Name (at: 3 Dec 2007, 10:15AM)",event.toString());
        event.markDone();
        assertEquals("[E][X] Event Name (at: 3 Dec 2007, 10:15AM)",event.toString());
    }


}
