import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String taskType = "E";
    String timeRange;
    LocalDate eventDate;

    public Event(String taskName, String dateTime) {
        super(taskName);
        String[] dateTimeSplit = dateTime.split(" ");
        this.eventDate = LocalDate.parse(dateTimeSplit[0]);
        this.timeRange = dateTimeSplit[1];
    }

    @Override
    public String markAsDone(boolean isDone) {
        if (isDone) {
            this.done = true;
            return  " Nice! I've marked this task as done:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (done ? "X" : " ") + "] " + // [X]
                    this.taskName;
        } else {
            this.done = false;
            return "OK, I've marked this task as not done yet:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (done ? "X" : " ") + "] " + // [X]
                    this.taskName;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy");
        String eventDateFormatted = eventDate.format(formatter);
        return "[" + this.taskType + "]"
                + "[" + (done ? "X" : " ") + "] "
                + this.taskName
                + "(at: " + eventDateFormatted + " " + this.timeRange + ")" +
                "\n";
    }
}
