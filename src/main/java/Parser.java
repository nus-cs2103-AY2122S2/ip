import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String[] parseCmdAndDes(String cmd) {
        return cmd.split(" ", 2);
    }

    public static String[] splitDeadlineAndTime(String cmd) {
        return cmd.split(" /by ", 2);
    }

    public static String[] splitEventAndTime(String cmd) {
        return cmd.split(" /at ", 2);
    }

    public static LocalDate parseDateTime(String dateTime) {
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateTime, myFormatter);
    }

    public static String[] splitDateAndTime(String dateTime) {
        return dateTime.split(" ", 2);
    }
}
