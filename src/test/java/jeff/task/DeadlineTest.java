package jeff.task;

import jeff.main.JeffException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testString() throws JeffException {
        String description = "testing";
        String dateInfo = "2/8/2019 2000";
        Deadline currTask = new Deadline(description, dateInfo);
        assertEquals("[D][ ] testing (by: Aug 02 2019 08:00 pm)", currTask.toString());
    }
}