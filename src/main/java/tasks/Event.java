package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;
import java.time.LocalTime;
public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time = null;

    public Event(String taskName, LocalDate date, LocalTime time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    public Event(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    public Event(String taskName, LocalDate date, LocalTime time, boolean isDone) {
        super(taskName, isDone);
        this.date = date;
        this.time = time;
    }

    public Event(String taskName, LocalDate date, boolean isDone) {
        super(taskName, isDone);
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        String returnString = "";
        String formatDate = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        returnString += "[E]" + super.toString() + " (at: " + formatDate;
        if (this.time != null) {
            String formatTime = this.time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            returnString += " " + formatTime;
        }
        returnString += ")";
        return returnString;
    }
}