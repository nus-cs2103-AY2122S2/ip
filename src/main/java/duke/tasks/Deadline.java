package duke.tasks;

import java.time.LocalDate;

/**
 * Deadline is a Task.
 * Deadline has a date as an end time.
 */
public class Deadline extends Task {
    private String time;
    // Keeping this for now since we do not know what to use LocalDate date for...
    private String deadline;
    private LocalDate date;

    /**
     * Constructor for Deadline.
     * This is a Task with a deadline.
     *
     * @param task     the task description
     * @param isDone   the mark status of this deadline
     * @param deadline the deadline of this Task
     */
    public Deadline(String task, boolean isDone, String deadline) {
        super(task, isDone);
        this.deadline = deadline;
        this.dateFormatter(deadline);
    }

    /**
     * Get the Command (in String form) to add this Deadline to the TaskList.
     * Useful for saving/reading from the save file.
     *
     * @return a String formatted specially for a Deadline.
     */
    public String getStringCmd() {
        // mark status | type | descriptor | deadline
        return super.getIsDone() + "&D&" + super.getTask() + "&" + this.deadline;
    }

    /**
     * Formats a String into proper date/time format into
     * the form: 2-12-2019
     *
     * @param dateTime String that is of the form 2/12/2019 1800
     */
    private void dateFormatter(String dateTime) {
        // the string is of the form: 2/12/2019 1800
        dateTime = dateTime.replace('/', '-');
        String[] splitDateTime = dateTime.split(" ");

        // Attempt to format the date. If it cannot be formatted, it means
        // User did not give in a proper date format
        try {
            this.date = LocalDate.parse(splitDateTime[0]);
            this.time = splitDateTime[1];
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Gets the LocalDate of this Deadline, in String format.
     *
     * @return the date, in String format
     */
    public String getDate() {
        return date.toString();
    }

    /**
     * Retrieves the (by: ...) part of a Deadline.
     *
     * @return back part of a Deadline
     */
    public String getDeadline() {
        return "(by:" + this.deadline + ")";
    }

    /**
     * Gets the String version of a Deadline
     *
     * @return String-formatted Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getDeadline();
    }
}
