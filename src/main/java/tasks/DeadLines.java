package tasks;

/**
 * A class that belongs to the Tasks Package.
 * This class encapsulates the logic of how DeadLines should be represented in Duke.
 */
public class DeadLines extends Tasks {
    private final String date;

    /**
     * Constructor for DeadLines.
     * @param task Task that should be recorded.
     * @param marked Boolean flag for whether a task is completed.
     * @param date Date for task to be done by.
     */
    public DeadLines(String task, Boolean marked, String date) {
        super(task, marked);
        this.date = date;
    }

    /**
     * Construct a string representation of DeadLine.
     * @return String representation of DeadLine for caching into a pre-constructed file.
     */
    @Override
    public String cacheString() {
        String s = getMarked() ? "1" : "0";
        return "D" + "|" + s + "|" + this.getTask() + "|" + returnDate(this.date);
    }

    /**
     * Message to be displayed for DeadLine as a Task in Duke.
     * @return String representation of DeadLine.
     */
    @Override
    public String toString() {
        if (this.getMarked()) {
            return "[D]" + "[X" + "] " + this.getTask() + " (by: " + returnDate(this.date) + ")";
        } else {
            return "[D]" + "[ " + "] " + this.getTask() + " (by: " + returnDate(this.date) + ")";
        }
    }
}
