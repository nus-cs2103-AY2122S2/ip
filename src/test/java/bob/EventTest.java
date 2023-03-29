package bob;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    private static final String VALID_TIME = "2022-09-09T23:09:00";
    private static final String INVALID_TIME = "invalid time";
    private static final String VALID_DESCRIPTION = "This is a valid description.";
    private static final String INVALID_DESCRIPTION = "";

    @Test
    public void constructor_emptyDescription_throwsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Event(INVALID_DESCRIPTION, VALID_TIME));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Event(INVALID_DESCRIPTION, VALID_TIME, true));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Event(INVALID_DESCRIPTION, VALID_TIME, false));
    }

    @Test
    public void constructor_invalidTime_throwsDateTimeParseException() {
        Assertions.assertThrows(DateTimeParseException.class, () ->
                new Event(VALID_DESCRIPTION, INVALID_TIME));
        Assertions.assertThrows(DateTimeParseException.class, () ->
                new Event(VALID_DESCRIPTION, INVALID_TIME, true));
        Assertions.assertThrows(DateTimeParseException.class, () ->
                new Event(VALID_DESCRIPTION, INVALID_TIME, false));
    }

    @Test
    public void isValidEvent() {
        final Event validTrueEvent = new Event(VALID_DESCRIPTION, VALID_TIME, true);
        final Event validFalseEvent = new Event(VALID_DESCRIPTION, VALID_TIME, false);

        final String validTrueEventSavedEntry = "E|1|" + VALID_DESCRIPTION + "/at " + VALID_TIME;
        final String validFalseEventSavedEntry = "E|0|" + VALID_DESCRIPTION + "/at " + VALID_TIME;

        Assertions.assertEquals(validTrueEvent.generateSavedEntry(), validTrueEventSavedEntry);
        Assertions.assertEquals(validFalseEvent.generateSavedEntry(), validFalseEventSavedEntry);

        final String validTrueEventToString = "[E][X] " + VALID_DESCRIPTION + " (at: Sep 9 2022, 11:09 pm)";
        final String validFalseEventToString = "[E][ ] " + VALID_DESCRIPTION + " (at: Sep 9 2022, 11:09 pm)";

        Assertions.assertEquals(validTrueEvent.toString(), validTrueEventToString);
        Assertions.assertEquals(validFalseEvent.toString(), validFalseEventToString);
    }
}
