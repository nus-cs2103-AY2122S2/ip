package myboss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a task with a specified date and time range of the task. An Event Object
 * corresponds to a task with a specified date and time range.
 */
public class Event extends Task {
    String timeRange;
    LocalDate eventDate;

    /**
     * Creates an Event Object with the specified task name and datetime.
     *
     * @param taskName name of the task.
     * @param dateTime date and time of the task.
     */
    public Event(String taskName, String dateTime) {
        super(taskName, "E");
        String[] dateTimeSplit = dateTime.split(" ");
        this.eventDate = LocalDate.parse(dateTimeSplit[0]);
        this.timeRange = dateTimeSplit[1];
    }

    /**
     * Creates an Event Object with the specified task name, datetime and whether task is done.
     *
     * @param taskName name of the task.
     * @param dateTime date and time range of the task.
     * @param isDone whether the task is done.
     */
    public Event(String taskName, String dateTime, boolean isDone) {
        super(taskName, "E", isDone);
        String[] dateTimeSplit = dateTime.split(" ");
        this.eventDate = LocalDate.parse(dateTimeSplit[0]);
        this.timeRange = dateTimeSplit[1];
    }

    /**
     * Creates an Event Object with the specified task name, date, time range and whether task is done.
     *
     * @param taskName name of the task.
     * @param date date of the task.
     * @param timeRange time range of the task.
     * @param isDone whether the task is done.
     */
    public Event(String taskName, String date, String timeRange, boolean isDone) {
        super(taskName, "E", isDone);
        this.eventDate = LocalDate.parse(date);
        this.timeRange = timeRange;
    }

    /**
     * Returns the string output of an Event Object when marked as done or not done.
     *
     * @param isDone whether task is to be marked as done or not done.
     * @return string output of an Event Object when marked as done or not done.
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return Objects.equals(timeRange, event.timeRange) && Objects.equals(eventDate, event.eventDate);
    }
}
