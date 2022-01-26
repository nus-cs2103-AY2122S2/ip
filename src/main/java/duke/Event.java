package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * event class
 * inherit from Task class
 */
public class Event extends Task {
    protected String at;
    protected String date;
    protected String time;

    public Event (String description, String at) {
        super(description);
        this.at = at;
        String[] dateTimeTemp = at.split(" ");
        this.date = dateTimeTemp[0];
        this.time = dateTimeTemp[1];
    }

    @Override
    public String message() {
        return "E | " + "[" +  this.getStatusIcon() + "] " + super.message() + "(at:" + dateTimeFormat(date) + " " + this.time + ")";
    }

    /**
     * re-format the date and time
     * @param dateTime
     * @return
     */
    public String dateTimeFormat(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate d = LocalDate.parse(dateTime,formatter);

        return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
