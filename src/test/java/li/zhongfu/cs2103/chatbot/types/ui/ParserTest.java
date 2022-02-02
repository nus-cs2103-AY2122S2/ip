package li.zhongfu.cs2103.chatbot.types.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setup() {
        parser = new Parser();
    }

    @Test
    public void parseArgString_blankString() {
        Map<String, String> result;

        result = parser.parseArgString("");
        assertEquals(1, result.size());
        assertEquals("", result.get(""));

        result = parser.parseArgString("  ");
        assertEquals(1, result.size());
        assertEquals("", result.get(""));
    }

    @Test
    public void parseArgString_posArgOnly() {
        Map<String, String> result;

        result = parser.parseArgString("testing one two");
        assertEquals(1, result.size());
        assertEquals("testing one two", result.get(""));

        result = parser.parseArgString(" with leading and trailing spaces? ");
        assertEquals(1, result.size());
        assertEquals("with leading and trailing spaces?", result.get(""));
    }

    @Test
    public void parseArgString_keywordArgs() {
        Map<String, String> result;

        result = parser.parseArgString(" /arg1  val1   /arg2 /arg3 val2");
        assertEquals(4, result.size());
        assertEquals("", result.get(""));
        assertEquals("val1", result.get("arg1"));
        assertEquals("", result.get("arg2"));
        assertEquals("val2", result.get("arg3"));

        result = parser.parseArgString(" here's a pos arg /and here are some /more keyword arguments  "
                + "/and_one_without_a_value   /one_more? why not ");
        assertEquals(5, result.size());
        assertEquals("here's a pos arg", result.get(""));
        assertEquals("here are some", result.get("and"));
        assertEquals("keyword arguments", result.get("more"));
        assertEquals("", result.get("and_one_without_a_value"));
        assertEquals("why not", result.get("one_more?"));
    }

    @Test
    public void parseDateTime_validFormats() {
        // todo random years/months/days/etc
        LocalDateTime expectedNoTime = LocalDateTime.of(2021, Month.NOVEMBER, 12, 0, 0);
        LocalDateTime expectedNoSeconds = LocalDateTime.of(2021, Month.NOVEMBER, 12, 13, 14);
        LocalDateTime expectedWithSeconds = LocalDateTime.of(2021, Month.NOVEMBER, 12, 13, 14, 15);

        assertEquals(expectedNoTime, parser.parseDateTime("2021-11-12"));
        assertEquals(expectedNoTime, parser.parseDateTime("nov 12 2021"));
        assertEquals(expectedNoTime, parser.parseDateTime("November 12 2021"));
        assertEquals(expectedNoTime, parser.parseDateTime("12 Nov 2021"));
        assertEquals(expectedNoTime, parser.parseDateTime("12 november 2021"));
        assertEquals(expectedNoSeconds, parser.parseDateTime("2021-11-12T13:14"));
        assertEquals(expectedNoSeconds, parser.parseDateTime("2021-11-12 13:14"));
        assertEquals(expectedNoSeconds, parser.parseDateTime("nov 12 2021 13:14"));
        assertEquals(expectedNoSeconds, parser.parseDateTime("November 12 2021 13:14"));
        assertEquals(expectedNoSeconds, parser.parseDateTime("12 Nov 2021 13:14"));
        assertEquals(expectedNoSeconds, parser.parseDateTime("12 november 2021 13:14"));
        assertEquals(expectedWithSeconds, parser.parseDateTime("2021-11-12T13:14:15"));
        assertEquals(expectedWithSeconds, parser.parseDateTime("2021-11-12 13:14:15"));
        assertEquals(expectedWithSeconds, parser.parseDateTime("nov 12 2021 13:14:15"));
        assertEquals(expectedWithSeconds, parser.parseDateTime("November 12 2021 13:14:15"));
        assertEquals(expectedWithSeconds, parser.parseDateTime("12 Nov 2021 13:14:15"));
        assertEquals(expectedWithSeconds, parser.parseDateTime("12 november 2021 13:14:15"));
    }

    @Test
    public void parseDateTime_invalidFormats() {
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("1 nov"));
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("2021-02-30T15:20"));
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("2021-02-10 15:20T14:30"));
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("2021-11-12T"));
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("2021-13-12 14:15"));
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("three years ago"));
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("13:14"));
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("31 feb 2021 13:14"));
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("11 nov 2021 25:21"));
        assertThrows(DateTimeParseException.class, () -> parser.parseDateTime("nov 3 2021 14:80"));
    }
}

