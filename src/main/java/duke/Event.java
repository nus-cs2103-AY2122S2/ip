package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents task of type event
 */
public class Event extends Task {

    /**
     * String of time indicating when task is occurring
     */
    private final String time;
    /**
     * Datetime indicating when task is occurring
     * Null if time cannot be converted to datetime
     */
    private LocalDateTime datetime;

    /**
     * Creation of Event Task
     *
     * @param description String of task description
     * @param time String representing when task is occurring
     */
    Event(String description, String time) {
        super(description);
        this.time = time;
        try {
            datetime = LocalDateTime.parse(time);
        } catch (DateTimeParseException e) {
            datetime = null;
        }
    }

    /**
     * Prints details of Event
     */
    public void print() {
        System.out.print("[E]");
        System.out.print("[" + (this.isCompleted ? "x" : " ") + "] " + this.description);
        if (datetime == null) {
            System.out.println(" (at: " + this.time + ")");
        } else {
            System.out.println(" (at: " + this.datetime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
                    + ")");
        }

    }

    @Override
    public String toString() {
        String description;
        description = "[E]" + "[" + (this.isCompleted ? "x" : " ") + "] " + this.description;
        if (datetime == null) {
            description += " (at: " + this.time + ")\n";
        } else {
            description += " (at: " + this.datetime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
                    + ")\n";
        }
        return description;
    }

    /**
     * Returns details of Event as a String array
     * Index 0: TaskType
     * Index 1: Completed
     * Index 2: Description
     * Index 3: Time
     *
     * @return String array of Event details
     */
    @Override
    public String[] getDetails() {
        String[] details = super.getDetails();
        details[0] = TaskType.EVENT.toString();
        details[3] = time;
        return details;
    }
}
