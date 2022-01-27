package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Date;

public class DateTimeParser {

    /**
     * Return a LocalDate object based on the input String
     *
     * @param date an String giving the date to be parsed
     * @return a LocalDate object from the parsed String.
     * @throws DateTimeParseException
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        //System.out.println(parsedDate);

        return parsedDate;
    }


    /**
     * Return a String from a LocalDate object, formatted in FULL form.
     *
     * @param date a LocalDate object
     * @return
     */
    public static String formatDate(LocalDate date) {
        //System.out.println(date);
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

    }


}
