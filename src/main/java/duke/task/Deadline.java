package duke.task;

import java.time.LocalDateTime;

/**
 * Deadline is a type of Task that contains addition.
 */
public class Deadline extends Task {

    private static final String TYPE = "D";

    private String info;

    private LocalDateTime dateInfo;

    /**
     * Constructor for deadline.
     * @param name name of task.
     * @param dateInfo additional dateInfo of the task.
     */
    public Deadline(String name, String dateInfo) {
        super(name);
        info = dateInfo;
        this.dateInfo = LocalDateTime.parse(dateInfo, this.DATE_TIME_FORMAT_IN);
    }

    /***
     * Overloaded Constructor
     * @param name
     * @param marked
     * @param dateInfo
     */
    public Deadline(String name, boolean marked, String dateInfo) {
        super(name);
        this.dateInfo = LocalDateTime.parse(dateInfo, this.DATE_TIME_FORMAT_IN);
        this.isMarked = marked;
    }

    /***
     * method to return the string representation of the storage task.
     * @return String representation of the storage task
     */
    @Override
    public String toStore() {
        return TYPE + " | " + this.markStore() + " | "
                + this.name + " | " + this.info;
    }
    /**
     * @return String representation of the task.
     */
    @Override
    public String display() {
        return "[" + TYPE + "] " + "[" + markDisplay() + "] "
                + this.name + " (by " + dateInfo.format(Task.DATE_TIME_FORMAT_OUT) + ")";
    }
}
