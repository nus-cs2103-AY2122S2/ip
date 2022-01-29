package duke.task;

import duke.DateHelper;

/**
 * This is a Task, specifically a Deadline task.
 * It only accepts the details and time of the deadline.
 */
public class Deadline extends Task {
    protected DateHelper by;
    private String type;

    /**
     * Constructor of the Deadline task.
     * Creates a Deadline task when a deadline command is passed by the user.
     *
     * @param description Description of the task.
     * @param date Formatted date that the user inputted to be the due date of the task.
     */
    public Deadline(String description, DateHelper date) {
        super(description);
        by = date;
        type = "D";
    }

    /**
     * Formats the deadline details that is printed out in the CLI
     *
     * @return Formatted deadline details [type][marked state] <description> (by: <dd/MMM/yyyy>)
     */
    @Override
    public String getTask() {
        return "[" + type +"]" + super.getTask() + " (by: " + by.getDatetime() + ")";
    }

    /**
     * Formats parts of the deadline details that is saved to the text file
     *
     * @return <type> | <description> | <due date>
     */
    @Override
    public String getDescription() {
        return type + " | " + description + " | " + by.getDatetime();
    }
}