package arthur.timings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    @Test
    public void dateTime_checkFormatting_success() {
        assertEquals("15 Apr 2022 04:25PM", new DateTime("2022-04-15 16:25").getString());
    }
}
