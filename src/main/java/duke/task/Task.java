package duke.task;

import java.time.LocalDate;

/**
 * Handles the creation of tasks.
 */
public class Task {
    private boolean isMarked;
    private final String description;
    private final String charId;
    private final LocalDate date;

    /**
     * Creates a Task object with the relevant details.
     *
     * @param description Description of the task.
     * @param charId A character corresponding to the type of task created.
     * @param date Date in which the task is due.
     */
    public Task(String description, String charId, LocalDate date) {
        this.isMarked = false;
        this.description = description;
        this.charId = charId;
        this.date = date;
    }

    /**
     * Marks the task as completed or incomplete.
     *
     * @param toMark A boolean to mark or unmark a task.
     * @param toShow A boolean to print Duke's reply to the user.
     */
    public String markTask(boolean toMark, boolean toShow) {
        this.isMarked = toMark;
        String output;

        if (toShow) {
            if (toMark) {
                output = "Nice! I've marked this task as done:\n";
            } else {
                output = "OK, I've marked this task as not done yet:\n";
            }
            return output + toString();
        }
        return "";
    }

    /**
     * Returns 'X' if the task is mark, a space, ' ' otherwise.
     *
     * @return 'X' if the task is mark, a space, ' ' otherwise.
     */
    public char getMark() {
        return this.isMarked ? 'X' : ' ';
    }

    /**
     * Returns a description of the task.
     *
     * @return A description of the task.
     */
    public String getUserInput() {
        return this.description;
    }

    /**
     * Returns the char representation of the task.
     *
     * @return The char representation of the task.
     */
    public String getCharId() {
        return this.charId;
    }

    /**
     * Returns the date in which the task is due.
     *
     * @return The date in which the task is due.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Prints out the details of the task.
     *
     * @return A string with details of the task.
     */
    @Override
    public String toString() {
        return "[" + getMark() + "] " + getUserInput();
    }
}
