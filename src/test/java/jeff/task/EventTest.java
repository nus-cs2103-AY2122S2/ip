package jeff.task;

import jeff.main.JeffException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testString() throws JeffException {
        String description = "testing";
        String dateInfo = "20-8-2019 1800";
        Event currTask = new Event(description, dateInfo);
        assertEquals("[E][ ] testing (at: Aug 20 2019 06:00 pm)", currTask.toString());
    }
}