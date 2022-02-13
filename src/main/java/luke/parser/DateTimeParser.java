package luke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * DateTime Parser with specific format.
 * Accepts numerous format as input, but output format only as [MMM dd yyyy HH:mm] for strings
 * and [dd/MM/yyyy HH:mm] for storing.
 */
public class DateTimeParser {

    private static final String ERROR_MESSAGE = "\"%s\" is not a valid argument for every.";
    private static Map<String, Function<LocalDateTime, LocalDateTime>> recurFunctionMap = new HashMap<>() {{
            put("day", dt -> dt.plusDays(1));
            put("week", dt -> dt.plusWeeks(1));
            put("month", dt -> dt.plusMonths(1));
            put("year", dt -> dt.plusYears(1));
        }};

    /**
     * Formatter containing all acceptable datetime format that user can pass as string.
     */
    private static DateTimeFormatter fromStringToDateTime = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy.MM.dd HHmm"))
            .toFormatter();

    /**
     * Specific format [MMM dd yyyy HH:mm] to convert all date to string.
     */
    private static DateTimeFormatter fromDateTimeToString = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Specific format [dd/MM/yyyy HH:mm] to convert all date to command string.
     */
    private static DateTimeFormatter fromDateTimeToCommand = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static Function<LocalDateTime, LocalDateTime> getDateTimeIncrementFunction(String every) {
        if (!recurFunctionMap.containsKey(every)) {
            throw new DateTimeParseException(String.format(ERROR_MESSAGE, every), every, 0);
        }

        return recurFunctionMap.get(every);
    }
    public static LocalDateTime toLocalDateTime(String date) throws DateTimeParseException {
        return LocalDateTime.parse(date, fromStringToDateTime);
    }

    public static String toString(LocalDateTime date) {
        return date.format(fromDateTimeToString);
    }

    public static String toCommandString(LocalDateTime date) {
        return date.format(fromDateTimeToCommand);
    }
}
