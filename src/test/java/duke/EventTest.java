package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void eventToString() {
        Event event = new Event("New event", "2/12/2022 1800", "2/12/2022 2000");
        Assertions.assertEquals("[E][ ] New event (at: 02/12/2022 1800 till 02/12/2022 2000)", event.toString());
        System.out.println("### event toString() successful! ###");
    }

    @Test
    void eventMarkComplete() {
        Event event = new Event("New event", "2/12/2022 1800", "2/12/2022 2000");
        event.markAsComplete();
        Assertions.assertEquals("[E][X] New event (at: 02/12/2022 1800 till 02/12/2022 2000)", event.toString());
        System.out.println("### event markAsComplete() successful! ###");
    }
}
