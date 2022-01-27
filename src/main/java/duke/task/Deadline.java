package duke.task;

import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Task object with non-null LocalDateTime variable.
 */
public class Deadline extends Task {
    /**
     * Instantiates a Deadline object with content and date.
     *
     * @param content String content.
     * @param date    LocalDateTime due date.
     */
    public Deadline(String content, LocalDateTime date) {
        super(content, date);
    }

    /**
     * Instantiates a Deadline object with content and date and isDone.
     *
     * @param content String content.
     * @param date    LocalDateTime due date.
     * @param isDone  Boolean to show if the task is done.
     */
    public Deadline(String content, LocalDateTime date, boolean isDone) {
        super(content, date, isDone);
    }

    /**
     * Returns the String representation of this class object.
     *
     * @return String String representation of Deadline.
     */
    @Override
    public String toString() {
        if (getIsDone()) {
            return "[D][X] " + getContent() + " (by: " + date
                    .format(Ui.formatter) + ")";
        } else {
            return "[D][ ] " + getContent() + " (by: " + date
                    .format(Ui.formatter) + ")";
        }
    }
}
