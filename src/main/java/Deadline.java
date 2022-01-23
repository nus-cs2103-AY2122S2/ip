import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

// Deadline class
public class Deadline extends Task {

    protected LocalDate by;
    protected LocalDateTime byTime;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (isDateFormat(by)) {
            this.by = LocalDate.parse(by, dateFormat);
        } else if (isDateTimeFormat(by)) {
            this.byTime = LocalDateTime.parse(by, dateTimeFormat);
        } else {
            throw new DukeException("The date format parsed is incorrect! It should be dd-MM-yyyy or dd-MM-yyyy HH:mm!");
        }
    }


    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + "," + by;
    }

    @Override
    public String toString() {
        if (by != null) {
            return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + formatDateTime(byTime) + ")";
        }
    }

    private boolean isDateTimeFormat(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, dateTimeFormat);
            return true;
        } catch (Exception e) {
            System.out.println("Not Datetime!");
        }
        return false;
    }

    private boolean isDateFormat(String dateString)  {
        try {
            LocalDate.parse(dateString, dateFormat);
            return true;
        } catch (Exception e) {
            System.out.println("Not Date!");
        }
        return false;
    }

    public String formatDateTime(LocalDateTime dateTime) {
        Integer day = dateTime.getDayOfMonth();
        Month month = dateTime.getMonth();
        Integer year = dateTime.getYear();
        Integer hour = dateTime.getHour();
        Integer minute = dateTime.getMinute();
        return day + " " + month + " " + year + " " + hour + ":" + minute;
    }

    public String formatDate(LocalDate date) {
        Integer day = date.getDayOfMonth();
        Month month = date.getMonth();
        Integer year = date.getYear();
        return day + " " + month + " " + year;
    }

}

