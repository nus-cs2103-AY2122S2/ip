package duke.task;

/**
 * A class that represents a Deadline task.
 */
public class Deadline extends TaskWithDateTime {
    /**
     * Constructor to initialize an instance of Deadline class with task description
     * and date/time input.
     *
     * @param description Description of the Deadline task
     * @param dateTimeInput Date/time by which the Deadline task needs to be completed
     */
    public Deadline(String description, String dateTimeInput) {
        super(TaskType.DEADLINE, description, dateTimeInput);
    }

    /**
     * Constructor to initialize an instance of Deadline class with task description,
     * date/time input and isDone flag.
     *
     * @param description Description of the Deadline task
     * @param dateTimeInput Date/time by which the Deadline task needs to be completed
     * @param isDone Flag to indicate if the Deadline task is done
     */
    public Deadline(String description, String dateTimeInput, boolean isDone) {
        super(TaskType.DEADLINE, description, dateTimeInput, isDone);
    }

    /**
     * Returns the date/time information by which the Deadline task needs to be completed.
     *
     * @return The string representation of the date/time information
     */
    @Override
    public String dateTimeInfo() {
        return "(by: " + super.getDateTimeOutput() + ")";
    }
}
