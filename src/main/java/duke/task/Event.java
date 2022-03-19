package duke.task;

import duke.DateHelper;

/**
 * This is a Task, specifically an Event task.
 * It only accepts the details and time of the event.
 */
public class Event extends Task {
    protected DateHelper at;
    private String type;

    /**
     * Constructor of the Event task.
     * Creates an Event task when an event command is passed by the user.
     *
     * @param description Description of the task.
     * @param date Formatted date that the user inputted to be the time of the task.
     */
    public Event(String description, DateHelper date) {
        super(description);
        at = date;
        type = "E";
    }

    /**
     * Formats the deadline details that is printed out in the CLI
     *
     * @return Formatted deadline details [type][marked state] <description> (at: <dd/MMM/yyyy>)
     */
    @Override
    public String getTask() {
        return "[" + type + "]" + super.getTask() + " (at: " + at.getDatetime() + ")";
    }

    /**
     * Formats parts of the event details that is saved to the text file
     *
     * @return <type> | <description> | <event date>
     */
    @Override
    public String getDescription() {
        return type + " | " + description + " | " + at.getDatetime();
    }
}