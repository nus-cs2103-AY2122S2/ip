package duke;
public class Event extends Task {

    protected String at;

    /**
     * constructor for Event
     * @param description string indicating the description of the task
     * @param at the time by which the task is supposed to be done
     * @param isDone boolean specifying if the task has been completed
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * constructor for Event
     * @param description string indicating the description of the task
     * @param at the time by which the task is supposed to be done
     */
    public Event(String description, String at) {
        super(description, false);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String parseString() {
        return "E |" + super.parseString() + " | " + at; 
    }
}
