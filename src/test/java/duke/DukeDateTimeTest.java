package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeDateTimeTest {

    @Test
    public void testParse() throws DukeException {
        assertEquals(new DukeDateTime(LocalDate.of(2021, 1, 24),
                LocalTime.of(7, 30)), DukeDateTime.parse("2021-1-24 7:30"));
        assertEquals(new DukeDateTime(LocalDate.of(2021, 1, 24)),
                DukeDateTime.parse("2021-1-24"));
        try {
            DukeDateTime.parse("");
        } catch (DukeException e) {
            assertEquals("Unrecognized time format. Valid formats:\nyyyy-M-d\nyyyy-M-d H:mm",
                    e.getMessage());
        }
    }

    @Test
    public void testFormat() {
        assertEquals("24 Jan 2021 7:30", new DukeDateTime(LocalDate.of(2021, 1, 24),
                LocalTime.of(7, 30)).format("d MMM yyyy"));
    }

    @Test
    public void testHasTime() {
        assertEquals(true, new DukeDateTime(LocalDate.of(2021, 1, 24),
                LocalTime.of(7, 30)).hasTime());
        assertEquals(false,
                new DukeDateTime(LocalDate.of(2021, 1, 24)).hasTime());
    }

    @Test
    public void testGetDate() {
        assertEquals(LocalDate.of(2021, 1, 24),
                new DukeDateTime(LocalDate.of(2021, 1, 24),
                        LocalTime.of(7, 30)).getDate());
    }

    @Test
    public void testGetTime() {
        assertEquals(LocalTime.of(7, 30),
                new DukeDateTime(LocalDate.of(2021, 1, 24),
                        LocalTime.of(7, 30)).getTime());
    }

}
