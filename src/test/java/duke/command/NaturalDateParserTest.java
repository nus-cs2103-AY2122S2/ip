package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.PrinterStub;

public class NaturalDateParserTest {
    @Test
    public void testStandardDate_validArgs_success() {
        assertEquals("2022-12-12T00:00",
                NaturalDateParser.getInstance().parseDate("12/12/2022").toString());
        assertEquals("2021-11-10T00:00",
                NaturalDateParser.getInstance().parseDate("10-11-2021").toString());
        assertEquals("2021-12-20T00:00",
                NaturalDateParser.getInstance().parseDate("20/12-2021").toString());
    }

    @Test
    public void testStandardDateTime_validArgs_success() {
        assertEquals("2022-12-12T12:34",
                NaturalDateParser.getInstance().parseDateTime("12/12/2022 12:34").toString());
        assertEquals("2021-11-10T02:35",
                NaturalDateParser.getInstance().parseDateTime("10-11-2021 2:35").toString());
        assertEquals("2021-12-20T02:02",
                NaturalDateParser.getInstance().parseDateTime("20/12-2021 2:2").toString());
    }

    @Test
    public void testStandardDate_invalidDate_nullReturned() {
        assertNull(NaturalDateParser.getInstance().parseDate("12/13/2022"));
        assertNull(NaturalDateParser.getInstance().parseDate("32/11/2022"));
        assertNull(NaturalDateParser.getInstance().parseDateTime("32/11/2022 12:00"));
        assertNull(NaturalDateParser.getInstance().parseDateTime("22/11/2022 25:00"));
        assertNull(NaturalDateParser.getInstance().parseDateTime("22/11/2022 12:69"));
        assertNull(NaturalDateParser.getInstance().parseDateTime("22/11/2022 25:69"));
    }

    @Test
    public void testNaturalDate_validArgs_success() {
        assertEquals("2023-01-20T00:00",
                NaturalDateParser.getInstance().parseDate("20 Jan 2023").toString());
        assertEquals("2023-02-05T00:00",
                NaturalDateParser.getInstance().parseDate("Feb 5 2023").toString());
        assertEquals("2023-03-05T00:00",
                NaturalDateParser.getInstance().parseDate("MaRcH 5 2023").toString());
        assertEquals("2023-05-20T00:00",
                NaturalDateParser.getInstance().parseDate("2023 May 20").toString());
        assertEquals("2023-10-02T00:00",
                NaturalDateParser.getInstance().parseDate("Oct 2023 02").toString());
        assertEquals("2023-10-02T00:00",
                NaturalDateParser.getInstance().parseDate("October 2023 02").toString());
    }

    @Test
    public void testNaturalDateTime_validArgs_success() {
        assertEquals("2023-01-20T12:34",
                NaturalDateParser.getInstance().parseDateTime("20 Jan 2023 12:34").toString());
        assertEquals("2023-01-20T12:34",
                NaturalDateParser.getInstance().parseDateTime("Jan 20 12:34 2023").toString());
        assertEquals("2023-01-20T12:34",
                NaturalDateParser.getInstance().parseDateTime("12:34 2023 Jan 20 ").toString());
        assertEquals("2023-01-20T23:53",
                NaturalDateParser.getInstance().parseDateTime("23:53 Jan 2023 20").toString());
    }

    @Test
    public void testNaturalDate_invalidMonth_nullReturned() {
        assertNull(NaturalDateParser.getInstance().parseDate("20 Nos 2023"));
        assertNull(NaturalDateParser.getInstance().parseDate("20 octopus 2024"));
    }

    @Test
    public void testNaturalDate_missingComponents_nullReturned() {
        assertNull(NaturalDateParser.getInstance().parseDate("Nov 2023"));
        assertNull(NaturalDateParser.getInstance().parseDate("20 December"));
        assertNull(NaturalDateParser.getInstance().parseDate("2023"));
    }

    @Test
    public void testNaturalDate_invalidDay_nullReturned() {
        assertNull(NaturalDateParser.getInstance().parseDate("32 Nov 2023"));
        assertNull(NaturalDateParser.getInstance().parseDate("0 Feb 2021"));
    }

    @Test
    public void testRelativeDate_validArgs_success() {
        LocalDateTime zeroedNow = LocalDate.now().atStartOfDay();
        assertEquals(zeroedNow.toString(),
                NaturalDateParser.getInstance().parseDate("today").toString());
        assertEquals(zeroedNow.plusDays(1).toString(),
                NaturalDateParser.getInstance().parseDate("tomorrow").toString());
        assertEquals(zeroedNow.withDayOfMonth(5).toString(),
                NaturalDateParser.getInstance().parseDate("5 this month").toString());
        assertEquals(zeroedNow.plusMonths(1).withDayOfMonth(10).toString(),
                NaturalDateParser.getInstance().parseDate("10 next month").toString());
        assertEquals(zeroedNow.withMonth(11).withDayOfMonth(13).toString(),
                NaturalDateParser.getInstance().parseDate("13 nov this year").toString());
        assertEquals(zeroedNow.plusYears(1).withMonth(12).withDayOfMonth(23).toString(),
                NaturalDateParser.getInstance().parseDate("23 Dec next year").toString());
    }
}
