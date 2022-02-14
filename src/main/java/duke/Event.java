package duke;

/**
 Class to represent the Event task
 Supports description of task and when the task is.
 */
public class Event extends Task {

    protected String eventDateTime;
    protected char type;

    /**
     * Constructor for event task
     *
     * @param description represents task item
     * @param eventDateTime task to be completed at
     *
     */
    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime;
        this.type = 'e';
    }

    /**
     *
     * Method to convert task to String type to be printed in the task list
     *
     * @return String of task item
     */
    public String toString() {
        return "[E]" + super.toString() + " (" + eventDateTime + ")";
    }
}
