/**
 * An abstract class that represents a task with date/time.
 */
public abstract class TaskWithDateTime extends Task {
    private String dateTimeInput;

    /**
     * Constructor to initialize an instance of TaskWithDateTime class with task
     * type, description and date/time input.
     *
     * @param type Type of task
     * @param description Description of the task
     * @param dateTimeInput Date/time input of the task
     */
    public TaskWithDateTime(TaskType type, String description, String dateTimeInput) {
        this(type, description, dateTimeInput, false);
    }

    /**
     * Constructor to initialize an instance of TaskWithDateTime class with task
     * type, description, date/time input and isDone flag.
     *
     * @param type Type of task
     * @param description Description of the task
     * @param dateTimeInput Date/time input of the task
     * @param isDone Flag to indicate if the task is done
     */
    public TaskWithDateTime(TaskType type, String description, String dateTimeInput, boolean isDone) {
        super(type, description, isDone);
        this.dateTimeInput = dateTimeInput;
    }

    /**
     * Returns the string representation of the date/time.
     *
     * @return The string representation of the date/time
     */
    protected String getDateTime() {
        String[] dateTimeParts = dateTimeInput.split(" ", 2);
        return dateTimeParts[1];
    }

    /**
     * An abstract method to return the date/time information of the task.
     * The method will be implemented in the extended classes.
     *
     * @return The string representation of the date/time information
     */
    public abstract String dateTimeInfo();

    /**
     * Returns the string representation of the task with date/time information.
     *
     * @return The string representation of the task with date/time information
     */
    @Override
    public String toString() {
        return super.toString() + dateTimeInfo();
    }

    /**
     * Returns the string representation of the task with date/time in save format.
     *
     * @return The string representation of the task with date/time to be saved
     */
    public String saveFormat() {
        return super.saveFormat() + " | " + dateTimeInput;
    }
}
