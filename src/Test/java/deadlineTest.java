import Duke.tasks.Deadline;
import Duke.tasks.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class deadlineTest {
    @Test
    @DisplayName("Deadline String")
    public void DeadlineString() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String item = "read";
        Date date = format.parse("18/01/1999");
        Deadline test = new Deadline(item, date);
        assertEquals("D | 0 | read | Mon 18/01/1999 0000",
                test.toString());
    }
}
