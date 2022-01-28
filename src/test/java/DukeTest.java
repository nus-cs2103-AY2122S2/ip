import duke.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void testEventTask() throws IOException, DukeException, ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");
        assertEquals("hello (at: 10/10/21 10:50 PM)", new Event("hello", formatter.parse("10/10/2021 10:50 PM")).getDescription());
    }

    @Test
    public void testToDoTask() {
        assertEquals("hello", new ToDos("hello").getDescription());
    }

    @Test
    public void testDeadlineTask() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");
        assertEquals("party (by: 10/10/21 10:50 PM)", new Deadline("party", formatter.parse("10/10/2021 10:50 PM")).getDescription());
    }


}

