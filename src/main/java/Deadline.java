import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String dateAndTime;

    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime.strip();
    }

    public String getParsedDateAndTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(this.dateAndTime);
            return localDateTime.format(dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date/Time format!");
        }
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + super.description + "(by: " + this.getParsedDateAndTime() + ")";
    }
}
