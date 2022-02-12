package duke.command.dateparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;

public class DateParserTest {
    @Test
    public void testStandardDate_validArgs_success() {
        assertEquals("2022-12-12T00:00",
                DateParser.getInstance().parseDate("12/12/2022").toString());
        assertEquals("2021-11-10T00:00",
                DateParser.getInstance().parseDate("10-11-2021").toString());
        assertEquals("2021-12-20T00:00",
                DateParser.getInstance().parseDate("20/12-2021").toString());
    }

    @Test
    public void testStandardDateTime_validArgs_success() {
        assertEquals("2022-12-12T12:34",
                DateParser.getInstance().parseDateTime("12/12/2022 12:34").toString());
        assertEquals("2021-11-10T02:35",
                DateParser.getInstance().parseDateTime("10-11-2021 2:35").toString());
        assertEquals("2021-12-20T02:02",
                DateParser.getInstance().parseDateTime("20/12-2021 2:2").toString());
    }

    @Test
    public void testStandardDate_invalidDate_nullReturned() {
        assertNull(DateParser.getInstance().parseDate("12/13/2022"));
        assertNull(DateParser.getInstance().parseDate("32/11/2022"));
        assertNull(DateParser.getInstance().parseDateTime("32/11/2022 12:00"));
        assertNull(DateParser.getInstance().parseDateTime("22/11/2022 25:00"));
        assertNull(DateParser.getInstance().parseDateTime("22/11/2022 12:69"));
        assertNull(DateParser.getInstance().parseDateTime("22/11/2022 25:69"));
    }

    @Test
    public void testNaturalDate_validArgs_success() {
        assertEquals("2023-01-20T00:00",
                DateParser.getInstance().parseDate("20 Jan 2023").toString());
        assertEquals("2023-02-05T00:00",
                DateParser.getInstance().parseDate("Feb 5 2023").toString());
        assertEquals("2023-03-05T00:00",
                DateParser.getInstance().parseDate("MaRcH 5 2023").toString());
        assertEquals("2023-05-20T00:00",
                DateParser.getInstance().parseDate("2023 May 20").toString());
        assertEquals("2023-10-02T00:00",
                DateParser.getInstance().parseDate("Oct 2023 02").toString());
        assertEquals("2023-10-02T00:00",
                DateParser.getInstance().parseDate("October 2023 02").toString());
    }

    @Test
    public void testNaturalDateTime_validArgs_success() {
        assertEquals("2023-01-20T12:34",
                DateParser.getInstance().parseDateTime("20 Jan 2023 12:34").toString());
        assertEquals("2023-01-20T12:34",
                DateParser.getInstance().parseDateTime("Jan 20 12:34 2023").toString());
        assertEquals("2023-01-20T12:34",
                DateParser.getInstance().parseDateTime("12:34 2023 Jan 20 ").toString());
        assertEquals("2023-01-20T23:53",
                DateParser.getInstance().parseDateTime("23:53 Jan 2023 20").toString());
    }

    @Test
    public void testNaturalDate_invalidMonth_nullReturned() {
        assertNull(DateParser.getInstance().parseDate("20 Nos 2023"));
        assertNull(DateParser.getInstance().parseDate("20 octopus 2024"));
    }

    @Test
    public void testNaturalDate_missingComponents_nullReturned() {
        assertNull(DateParser.getInstance().parseDate("Nov 2023"));
        assertNull(DateParser.getInstance().parseDate("20 December"));
        assertNull(DateParser.getInstance().parseDate("2023"));
    }

    @Test
    public void testNaturalDate_invalidDay_nullReturned() {
        assertNull(DateParser.getInstance().parseDate("32 Nov 2023"));
        assertNull(DateParser.getInstance().parseDate("0 Feb 2021"));
    }

    @Test
    public void testRelativeDate_validArgs_success() {
        LocalDateTime zeroedNow = LocalDate.now().atStartOfDay();
        assertEquals(zeroedNow.toString(),
                DateParser.getInstance().parseDate("today").toString());
        assertEquals(zeroedNow.plusDays(1).toString(),
                DateParser.getInstance().parseDate("tomorrow").toString());
        assertEquals(zeroedNow.withDayOfMonth(5).toString(),
                DateParser.getInstance().parseDate("5 this month").toString());
        assertEquals(zeroedNow.plusMonths(1).withDayOfMonth(10).toString(),
                DateParser.getInstance().parseDate("10 next month").toString());
        assertEquals(zeroedNow.withMonth(11).withDayOfMonth(13).toString(),
                DateParser.getInstance().parseDate("13 nov this year").toString());
        assertEquals(zeroedNow.plusYears(1).withMonth(12).withDayOfMonth(23).toString(),
                DateParser.getInstance().parseDate("23 Dec next year").toString());
    }

    @Test
    public void testDayDate_validArgs_success() {
        LocalDateTime zeroedNow = LocalDate.now().atStartOfDay();
        LocalDateTime nextMon = zeroedNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDateTime nextTue = zeroedNow.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        LocalDateTime nextWed = zeroedNow.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        LocalDateTime nextFri = zeroedNow.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        assertEquals(nextMon.toString(),
                DateParser.getInstance().parseDate("mon").toString());
        assertEquals(nextTue.toString(),
                DateParser.getInstance().parseDate("tuesday").toString());
        assertEquals(nextWed.withHour(12).withMinute(34).toString(),
                DateParser.getInstance().parseDateTime("wed 12:34").toString());
        assertEquals(nextFri.withHour(12).withMinute(34).toString(),
                DateParser.getInstance().parseDateTime("FrIdAy 12:34").toString());

    }
}
