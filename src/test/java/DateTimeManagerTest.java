import duke.managers.DateTimeManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class DateTimeManagerTest {
    @Test
    public void parseDateTimeStringTest() {
        String strInput = "14/02/2022 1212";
        String strOutput = "Monday, 14 Feb 2022 12:12";
        LocalDateTime localDateTime = DateTimeManager.parseString(strInput);
        assertEquals(strInput, DateTimeManager.getOriginalString(localDateTime));
        assertEquals(strOutput, DateTimeManager.getDisplayString(localDateTime));
    }
}
