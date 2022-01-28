package myboss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String timeRange;
    LocalDate eventDate;

    public Event(String taskName, String dateTime) {
        super(taskName, "E");
        String[] dateTimeSplit = dateTime.split(" ");
        this.eventDate = LocalDate.parse(dateTimeSplit[0]);
        this.timeRange = dateTimeSplit[1];
    }

    public Event(String taskName, String dateTime, boolean isDone) {
        super(taskName, "E", isDone);
        String[] dateTimeSplit = dateTime.split(" ");
        this.eventDate = LocalDate.parse(dateTimeSplit[0]);
        this.timeRange = dateTimeSplit[1];
    }

    public Event(String taskName, String dateTime, String timeRange, boolean isDone) {
        super(taskName, "E", isDone);
        this.eventDate = LocalDate.parse(dateTime);
        this.timeRange = timeRange;
    }

    @Override
    public String markAsDone(boolean isDone) {
        if (isDone) {
            this.isDone = true;
            return  " Nice! I've marked this task as done:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (this.isDone ? "X" : " ") + "] " + // [X]
                    this.taskName;
        } else {
            this.isDone = false;
            return "OK, I've marked this task as not done yet:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (this.isDone ? "X" : " ") + "] " + // [X]
                    this.taskName;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy");
        String eventDateFormatted = eventDate.format(formatter);
        return "[" + this.taskType + "]"
                + "[" + (isDone ? "X" : " ") + "] "
                + this.taskName
                + " (at: " + eventDateFormatted + " " + this.timeRange + ")" +
                "\n";
    }
}
