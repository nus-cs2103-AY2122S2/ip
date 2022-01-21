import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {
    public static String printDate(LocalDateTime date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return date.format(format);
    }
}