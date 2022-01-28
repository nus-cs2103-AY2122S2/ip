
package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import exceptions.DukeException;

public class DateValidator {

    public static LocalDate convertDate(final String date) throws DukeException {

        boolean valid = false;

        try {

            // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
            LocalDate convertDate = LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );

            return convertDate;

        } catch (DateTimeParseException e) {
            throw new DukeException("Put the date in this format YYYY-MM-DD");
        }
    }
}