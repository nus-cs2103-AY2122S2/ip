package duke;

/**
 * Represents Deadlines class that inherits from Task class.
 */
public class Deadlines extends Task {
    private final String time;
    private final String date;

    /**
     * Constructor used to instantiate a Deadlines class normally.
     *
     * @param description A String representing the description of the task.
     * @param date A String representing the date of the task.
     * @param time A String representing the time of the task.
     */
    public Deadlines(String description, String date, String time) {
        super(description);
        this.time = time;
        this.date = date;
    }

    /**
     * An alternative constructor used to instantiate a Deadlines class when loading data from a pre-existing
     * duke.txt file at the start of a new Duke session.
     *
     * @param mark An integer indicating if the task was done or not.
     * @param description A String representing the description of the task.
     * @param date A String representing the date of the task.
     * @param time A String representing the time of the task.
     */
    public Deadlines(int mark, String description, String date, String time) {
        super(description, mark);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns the String representation of the Deadline class for user to read.
     *
     * @return A String representing the Deadline class for the user.
     */
    public String getDeadline() {
        return "[D]" + this.getTask() + "(by: " + this.date + ", " + this.time + ")\n";
    }

    /**
     * Returns the String presentation of the Deadline class for writing into the duke.txt file.
     *
     * @return A String representing the Deadline class for the duke.txt file.
     */
    public String getFormattedText() {
        return "D>" + this.getMark() + ">" + this.getDescription() + ">" + this.date + ">" + this.time;
    }
}
