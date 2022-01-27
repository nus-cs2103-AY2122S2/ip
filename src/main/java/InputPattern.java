import java.util.regex.Pattern;

public final class InputPattern {
    private final static Pattern DATE_TIME_FORMAT = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");

    public static boolean isValidDateTimeString(String dateTime) {
        return DATE_TIME_FORMAT.matcher(dateTime).matches();
    }
}
