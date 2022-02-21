package duke;

/**
 * Event class
 */
public class Event extends Task {

    protected String at;
    public boolean isDone;

    /**
     * Constructor for event class
     * @param description task description
     * @param at date and time of event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.isDone = false;
    }

    /**
     * Constructor with ability to indicate whether event has
     * occurred or not
     */
    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Outputs the event in a format that can be saved
     * @return event as a string to be saved
     */
    public String toSave() {
        int isDoneNumber;
        if(isDone) {
            isDoneNumber = 1;
        } else {
            isDoneNumber = 0;
        }
        return "E | " + isDoneNumber + " | " + description +
                    "|" + at + System.lineSeparator();
    }

    /**
     * Returns event as a string
     * @return event as a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}