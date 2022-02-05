package gene.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTaskTest {
    @Test
    void toStringTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        Assertions.assertEquals("[E][ ] Test mark event (at: Monday, Dec 02, 2019 18:00 pm)", new EventTask("Test mark event", LocalDateTime.parse("2/12/2019 1800", formatter)).toString());
    }

    @Test
    void markTaskTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        Assertions.assertEquals("[E][X] Test mark event (at: Monday, Dec 02, 2019 18:00 pm)", new EventTask("Test mark event", LocalDateTime.parse("2/12/2019 1800", formatter)).markTask().toString());
    }

    @Test
    void unmarkTaskTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        Assertions.assertEquals("[E][ ] Test mark event (at: Monday, Dec 02, 2019 18:00 pm)", new EventTask("Test mark event", LocalDateTime.parse("2/12/2019 1800", formatter)).markTask().unmarkTask().toString());
    }
}