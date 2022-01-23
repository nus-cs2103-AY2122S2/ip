import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {

    protected LocalDate at;
    protected LocalDateTime atTime;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    public Event(String description, String at) throws DukeException {
        super(description);
        if (isDateFormat(at)) {
            this.at = LocalDate.parse(at, dateFormat);
        } else if (isDateTimeFormat(at)) {
            this.atTime = LocalDateTime.parse(at, dateTimeFormat);
        } else {
            throw new DukeException("The date format parsed is incorrect! It should be dd-MM-yyyy or dd-MM-yyyy HH:mm!");
        }
    }

    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + "," + at;
    }

    @Override
    public String toString() {
        if (this.at != null) {
            return "[E]" + super.toString() + " (at: " + formatDate(at) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + formatDateTime(atTime) + ")";
        }
    }

    private boolean isDateTimeFormat(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, dateTimeFormat);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Not Datetime!");
        }
        return false;
    }


    private boolean isDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString, dateFormat);
            return true;
        } catch (DateTimeParseException e) {
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
