package bob;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static final String VALID_TIME = "2022-09-09T09:09:00";
    private static final String INVALID_TIME = "invalid time";
    private static final String VALID_DESCRIPTION = "This is a valid description.";
    private static final String INVALID_DESCRIPTION = "";

    @Test
    public void constructor_emptyDescription_throwsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Deadline(INVALID_DESCRIPTION, VALID_TIME));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Deadline(INVALID_DESCRIPTION, VALID_TIME, true));
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Deadline(INVALID_DESCRIPTION, VALID_TIME, false));
    }

    @Test
    public void constructor_invalidTime_throwsDateTimeParseException() {
        Assertions.assertThrows(DateTimeParseException.class, () ->
                new Deadline(VALID_DESCRIPTION, INVALID_TIME));
        Assertions.assertThrows(DateTimeParseException.class, () ->
                new Deadline(VALID_DESCRIPTION, INVALID_TIME, true));
        Assertions.assertThrows(DateTimeParseException.class, () ->
                new Deadline(VALID_DESCRIPTION, INVALID_TIME, false));
    }

    @Test
    public void isValidDeadline() {
        final Deadline validTrueDeadline = new Deadline(VALID_DESCRIPTION, VALID_TIME, true);
        final Deadline validFalseDeadline = new Deadline(VALID_DESCRIPTION, VALID_TIME, false);

        final String validTrueDeadlineSavedEntry = "D|1|" + VALID_DESCRIPTION + "/by " + VALID_TIME;
        final String validFalseDeadlineSavedEntry = "D|0|" + VALID_DESCRIPTION + "/by " + VALID_TIME;

        Assertions.assertEquals(validTrueDeadline.generateSavedEntry(), validTrueDeadlineSavedEntry);
        Assertions.assertEquals(validFalseDeadline.generateSavedEntry(), validFalseDeadlineSavedEntry);

        final String validTrueDeadlineToString = "[D][X] " + VALID_DESCRIPTION + " (by: Sep 9 2022, 09:09 am)";
        final String validFalseDeadlineToString = "[D][ ] " + VALID_DESCRIPTION + " (by: Sep 9 2022, 09:09 am)";

        Assertions.assertEquals(validTrueDeadline.toString(), validTrueDeadlineToString);
        Assertions.assertEquals(validFalseDeadline.toString(), validFalseDeadlineToString);
    }
}
