package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline type of Task, with a date and time that the task must be completed.
 */
public class Deadline extends Task {
    private LocalDate date;
    private String deadline;

    /**
     * Constructor for Deadline.
     *
     * @param name
     * @param deadline
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
        //try parsing the eventTime as a date to set date
        try {
            this.date = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            //dont set date if not in right format
        }
    }

    /**
     * Constructor for Deadline with completion status.
     *
     * @param name
     * @param deadline
     * @param isCompleted
     */
    public Deadline(String name, String deadline, boolean isCompleted) {
        super(name, isCompleted);
        this.deadline = deadline;
    }


    /**
     * Returns the file string representation of this deadline.
     */
    @Override
    public String toFileString() {
        return "D : " + (isCompleted ? "1 : " : "0 : ") + name + " : " + deadline;
    }

    /**
     * Overriden toString method for deadline object.
     *
     * @return String representation of deadline object
     */
    @Override
    public String toString() {
        String str = "[D]";
        if (this.isCompleted) {
            str += "[X] ";
        } else {
            str += "[ ] ";
        }
        str += name + " ";
        //prints out special date format if it was entered as a recognised date format initially.
        str += "(by: " + (date != null ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadline) + ")";
        return str;
    }

}
