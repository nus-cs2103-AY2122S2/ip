/**
 * Represent the event of task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event.
     *
     * @param description description of the task.
     * @param at by when the event at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * The String representation of Event in the save file.
     *
     * @return the formats of the String to be save in the file
     */
    @Override
    public String saveToFileString() {
        return "E|" + (isDone ? "1|" : "0|") + super.getDescription() + "|" + at + "\n";
    }

    /**
     * The String representation of event.
     *
     * @return [E] with the status and description of the task,
     *         and at when.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

