package mike;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event (subclass of task).
 */
public class Event extends Task {
    private final String eventTime;
    private final LocalDate date;
    private static final String taskType = "E";

    private static LocalDate convertToDate(String endDate) {
        return LocalDate.parse(endDate);
    }

    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
        this.date = convertToDate(eventTime);
    }

    public Event(String name, String eventTime, boolean isDone) {
        super(name, isDone);
        this.eventTime = eventTime;
        this.date = convertToDate(eventTime);
    }

    /**
     * Returns a new event object with the isDone field set to true.
     *
     * @return Event object with isDone set to true.
     */
    public Event markAsDone() {
        return new Event(this.name, this.eventTime, true);
    }

    /**
     * Returns a new event object with the isDone field set to false.
     *
     * @return Event object with isDone set to false.
     */
    public Event markAsUndone() {
        return new Event(this.name, this.eventTime, false);
    }

    public String convertToStoredTaskFormat() {
        String doneIndicator = "false";
        if (super.isDone) {
            doneIndicator = "true";
        }
        String storedListFormat = String.format("%s|%s|%s|%s", taskType,
                doneIndicator, super.name, this.date);
        return storedListFormat;
    }

    @Override
    public String toString() {
        String doneMark;
        if (super.isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }
        String dateOutput = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[%s][%s] %s (at: %s)",
                taskType, doneMark, super.name, dateOutput);
    }
}
