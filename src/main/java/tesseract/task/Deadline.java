package tesseract.task;

import tesseract.main.Date;

/**
 * Represent a deadline, which is a type of task.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Deadline extends Task {
    private static final String MEMORY_FORMAT = "D@%s@%s";
    private static final String STRING_FORMAT = "[D]%s (by: %s)";
    private final String by;
    private final Date date;


    /**
     * Create a deadline that needs to be finished before a specific date.
     *
     * @param description A description of the deadline.
     * @param by Date by which the deadline needs to be finished.
     */
    public Deadline(String description, String by) {
        super(description);
        this.date = new Date(by);
        this.by = by;
    }

    /**
     * Convert the deadline to a string in the format of storage memory.
     *
     * @return Memory representation of the deadline.
     */
    @Override
    public String toMemoryString() {
        String output = String.format(MEMORY_FORMAT, super.toMemoryString(), this.by);
        return archiveString(output);
    }

    /**
     * Check if the deadline ends on a specific date.
     *
     * @param date An input date to check with the instance.
     * @return True if the deadline ends on the input date.
     */
    @Override
    public boolean isOn(Date date) {
        return this.date.equals(date);
    }

    /**
     * Convert the deadline to a string for UI.
     *
     * @return The UI representation of the deadline.
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, super.toString(), date.formattedTime());
    }
}
