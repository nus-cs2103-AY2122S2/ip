package duke;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void eventToString() {
        LocalDateTime startDateTime = Parser.parseDateTime("2/12/2022 1800");
        LocalDateTime endDateTime = Parser.parseDateTime("2/12/2022 2000");
        Event event = new Event("New event", startDateTime, endDateTime);
        Assertions.assertEquals("[E][ ] New event (at: 2/12/2022 1800 till 2/12/2022 2000)", event.toString());
        System.out.println("### event toString() successful! ###");
    }

    @Test
    void eventMarkComplete() {
        LocalDateTime startDateTime = Parser.parseDateTime("2/12/2022 1800");
        LocalDateTime endDateTime = Parser.parseDateTime("2/12/2022 2000");
        Event event = new Event("New event", startDateTime, endDateTime);
        event.markAsComplete();
        Assertions.assertEquals("[E][X] New event (at: 2/12/2022 1800 till 2/12/2022 2000)", event.toString());
        System.out.println("### event markAsComplete() successful! ###");
    }
}
