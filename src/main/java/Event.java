/**
 * A class that represents an Event task.
 */
public class Event extends TaskWithDateTime {
    /**
     * Constructor to initialize an instance of Event class with task description
     * and date/time input.
     *
     * @param description Description of the Event task
     * @param dateTimeInput Date/time at which the Event task is taking place
     */
    public Event(String description, String dateTimeInput) {
        super(TaskType.EVENT, description, dateTimeInput);
    }

    /**
     * Constructor to initialize an instance of Event class with task description,
     * date/time input and isDone flag.
     *
     * @param description Description of the Event task
     * @param dateTimeInput Date/time at which the Event task is taking place
     * @param isDone Flag to indicate if the Event task is done
     */
    public Event(String description, String dateTimeInput, boolean isDone) {
        super(TaskType.EVENT, description, dateTimeInput, isDone);
    }

    /**
     * Returns the date/time information at which the Event task is taking place.
     *
     * @return The string representation of the date/time information
     */
    @Override
    public String dateTimeInfo() {
        return "(at: " + super.getDateTimeOutput() +")";
    }
}
