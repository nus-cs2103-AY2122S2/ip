import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private final String sym = "E";
//    private String dayAndTime;
    private final LocalDate dayAndTime;


    Event (String description, String dayAndTime)  throws DateTimeException {
        super(description);
        this.dayAndTime = LocalDate.parse(dayAndTime);
    }

    String getSym() {
        return this.sym;
    }

    @Override
    public String toString() {
//        return String.format("[%s][%s] %s (at:%s)", sym, super.getStatusIcon(), super.getDescription(), this.dayAndTime);
        return String.format("[%s][%s] %s (at:%s)", sym, super.getStatusIcon(), super.getDescription(),
                    this.dayAndTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
    }
}
