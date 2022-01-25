package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;
import java.time.LocalTime;
public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time = null;

    public Deadline(String taskName, LocalDate date, LocalTime time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    public Deadline(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    public Deadline(String taskName, LocalDate date, LocalTime time, boolean isDone) {
        super(taskName, isDone);
        this.date = date;
        this.time = time;
    }

    public Deadline(String taskName, LocalDate date, boolean isDone) {
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
        returnString += "[D]" + super.toString() + " (at: " + formatDate;
        if (this.time != null) {
            String formatTime = this.time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            returnString += " " + formatTime;
        }
        returnString += ")";
        return returnString;
    }
}