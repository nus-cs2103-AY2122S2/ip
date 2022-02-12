package tasks;

/**
 * A class that belongs to the Tasks Package.
 * This class encapsulates the logic of how Events should be represented in Duke.
 */
public class Events extends Tasks {
    private final String date;

    /**
     * Constructs Events.
     * @param task Task that should be recorded.
     * @param marked Boolean flag for whether a task is completed.
     * @param date The date where the event will happen.
     */
    public Events(String task, Boolean marked, String date) {
        super(task, marked);
        this.date = date;
    }

    /**
     * Constructs a string representation of an Event.
     * @return String representation of Event for caching into a pre-constructed file.
     */
    @Override
    public String cacheString() {
        String s = getIsMarked() ? "1" : "0";
        return "E" + "|" + s + "|" + this.getTask() + "|" + returnDate(this.date);
    }

    /**
     * Creates message to be displayed for Event.
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        if (this.getIsMarked()) {
            return "[E]" + "[X" + "] " + this.getTask() + " (at: " + returnDate(this.date) + ")";
        } else {
            return "[E]" + "[ " + "] " + this.getTask() + " (at: " + returnDate(this.date) + ")";
        }
    }
}
