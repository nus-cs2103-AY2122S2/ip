package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task of type deadline
 */
public class Deadline extends Task {

    static final int TASK_INDEX = 0;
    static final int TIME_INDEX = 3;

    /**
     * String of time indicating when task is due
     */
    private final String time;
    /**
     * Datetime indicating when task is due
     * Null if time cannot be converted to datetime
     */
    private LocalDateTime datetime;

    /**
     * Creation of Deadline Task
     *
     * @param description String of task description
     * @param time String representing when task is due
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
        datetime = Parser.parseTime(time);
    }

    @Override
    public String toString() {
        String description;
        description = "[D]" + "[" + (this.isCompleted ? "x" : " ") + "] " + this.description;
        if (datetime == null) {
            description += " (by: " + this.time + ")\n";
        } else {
            description += " (by: " + this.datetime.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"))
                    + ")\n";
        }
        return description;
    }

    /**
     * Returns details of Deadline as a String array
     *      * Index 0: TaskType
     *      * Index 1: Completed
     *      * Index 2: Description
     *      * Index 3: Time
     *
     * @return String array of Deadline details
     */
    @Override
    public String[] getDetails() {
        String[] details = super.getDetails();
        details[TASK_INDEX] = TaskType.DEADLINE.toString();
        details[TIME_INDEX] = time;
        return details;
    }
}
