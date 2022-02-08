import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.ToDos;



public class DukeTest {

    @Test
    public void testEventTask() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");
        assertEquals("hello (at: 10/10/21 10:50 PM)",
                new Event("hello",
                        formatter.parse("10/10/2021 10:50 PM"), Task.Priority.HIGH).getDescription());
    }

    @Test
    public void testToDoTask() {
        assertEquals("hello", new ToDos("hello", Task.Priority.HIGH).getDescription());
    }

    @Test
    public void testDeadlineTask() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");
        assertEquals("party (by: 10/10/21 10:50 PM)",
                new Deadline("party", formatter.parse("10/10/2021 10:50 PM"), Task.Priority.HIGH).getDescription());
    }


}

