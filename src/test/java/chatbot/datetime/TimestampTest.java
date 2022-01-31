package chatbot.datetime;

import chatbot.exception.ChatBotException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class TimestampTest {

    private static final String DATE = "23/3/2023";
    private static final String TIME = "1800";
    private static final LocalDate LOCAL_DATE = LocalDate.of(2023, 3, 23);
    private static final LocalTime LOCAL_TIME = LocalTime.of(18, 0);

    @Test
    public void testDate() throws ChatBotException {
        Timestamp date = new Timestamp(DATE);

        assertEquals(LOCAL_DATE, date.getDate());
        assertNull(date.getTime());
        assertEquals("23 March 2023", date.toString());
        assertEquals(DATE, date.toSaveString());

        ChatBotException thrown = assertThrows(ChatBotException.class, () ->
            new Timestamp("100/100/100")
        );
        assertEquals("That's an invalid date format traveller!", thrown.getMessage());
    }

    @Test
    public void testDateTime() throws ChatBotException {
        String datetimeString = DATE.concat(" ").concat(TIME);
        Timestamp datetime = new Timestamp(datetimeString);

        assertEquals(LOCAL_DATE, datetime.getDate());
        assertEquals(LOCAL_TIME, datetime.getTime());
        assertEquals("23 March 2023, 6:00 PM", datetime.toString());
        assertEquals(datetimeString, datetime.toSaveString());

        ChatBotException thrown;
        thrown = assertThrows(ChatBotException.class, () ->
            new Timestamp(DATE.concat(" ").concat("2500"))
        );
        assertEquals("That's an invalid time format traveller!", thrown.getMessage());

        thrown = assertThrows(ChatBotException.class, () ->
            new Timestamp("timestamp")
        );
        assertEquals("That's an invalid date format traveller!", thrown.getMessage());
    }
}
