package jeff.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeff.main.JeffException;


public class EventTest {

    @Test
    public void testString() throws JeffException {
        String description = "testing";
        String dateInfo = "20-8-2019 1800";
        Event currTask = new Event(description, dateInfo);
        assertEquals("[E][ ] testing (at: Aug 20 2019 06:00 pm)", currTask.toString());
    }
}
