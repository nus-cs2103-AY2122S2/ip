import Duke.tasks.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class eventTest {
    @Test
    @DisplayName("Event String")
    public void EventString() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String item = "read";
        Date date = format.parse("18/01/1999");
        Event test = new Event(item, date);
        assertEquals("E | 0 | read | Mon Jan 18 00:00:00 SGT 1999",
                test.toString());
    }
}