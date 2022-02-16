import Duke.tasks.Deadline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class deadlineTest {
    @Test
    @DisplayName("Deadline String")
    public void DeadlineString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        String item = "read";
        LocalDateTime localDateTime = LocalDateTime.parse("18/01/1999 0000", format);
        Deadline test = new Deadline(item, localDateTime);
        assertEquals("D | 0 | read | Mon 18/01/1999 0000",
                test.toString());
    }
}
