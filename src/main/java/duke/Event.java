package duke;
/**
 Class to represent the Event task
 Supports description of task and when the task is.
 */
public class Event extends Task {
    protected String at;
    char type;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = 'e';
    }

    /**
     *
     * Method to convert task to String type to be printed in the task list
     *
     */
    public String toString() {
        return "[E]" + super.toString() + " (" + at + ")";
    }
}
