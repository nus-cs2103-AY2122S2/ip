import Duke.tasks.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class eventTest {
    @Test
    @DisplayName("Event String")
    public void EventString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String item = "read";
        LocalDateTime localDateTime = LocalDateTime.parse("18/01/1999 0000", format);
        Event test = new Event(item, localDateTime);
        assertEquals("E | 0 | read | Mon 18/01/1999 0000",
                test.toString());
    }
}