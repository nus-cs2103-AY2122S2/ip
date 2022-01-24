//@@author junrong98-reused
//Reused from https://nus-cs2103-ay2122s2.github.io/website/admin/ip-w2.html
// with minor modifications

/**
 * The Event class which inherits from Task.
 */
public class Event extends Task {

    protected String by;

    /**
     * The constructor class for Event
     * @param description The description of task sent by the user
     * @param by The deadline which the task should be completed by
     */
    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}