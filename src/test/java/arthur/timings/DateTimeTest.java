package arthur.timings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {
    @Test
    public void dateTime_checkFormatting_success() {
        assertEquals("15 Apr 2022 04:25PM", new DateTime("2022-04-15 16:25").getString());
    }
}
