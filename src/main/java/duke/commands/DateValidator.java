package duke.commands;

import java.time.LocalDate;

/**
 * The interface that validates dates in user inputs.
 */
public interface DateValidator {
    /**
     * Validates a local date in string format.
     *
     * @param dateStr the date string
     * @return date encapsulated in a LocalDate object
     */
    LocalDate validDate(String dateStr);
}
