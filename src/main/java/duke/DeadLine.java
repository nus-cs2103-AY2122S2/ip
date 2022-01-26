package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * deadline class
 * inherit from Task class
 */

public class DeadLine extends Task {

    protected String by;
    protected String date;
    protected String time;

    public DeadLine(String description, String by) {
        super(description);
        this.by = by;
        String[] dateTimeTemp = by.split(" ");
        this.date = dateTimeTemp[0];
        this.time = dateTimeTemp[1];
    }

    @Override
    public String message() {
        return "D | " + "[" +  this.getStatusIcon() + "] " + super.message()
                + "(by:" + dateTimeFormat(date) + " " + time + ")";
    }

    /**
     * re-format the date and time
     * @param dateTime
     * @return formated date and time
     */
    public String dateTimeFormat(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate d = LocalDate.parse(dateTime,formatter);

       return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
