package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.ui.Ui;


/**
 * Task object with non-null LocalDateTime variable.
 */
public class Deadline extends Task {
    /**
     * Instantiates a Deadline object with content and date.
     *
     * @param content String content.
     * @param date    LocalDateTime due date.
     * @param ui      Ui UI object.
     */
    public Deadline(String content, LocalDateTime date, Ui ui) {
        super(content, date, ui);
    }

    /**
     * Instantiates a Deadline object with content and date and isDone.
     *
     * @param content String content.
     * @param date    LocalDateTime due date.
     * @param isDone  Boolean to show if the task is done.
     * @param ui      Ui UI object.
     */
    public Deadline(String content, LocalDateTime date, boolean isDone, Ui ui) {
        super(content, date, isDone, ui);
    }

    /**
     * Instantiates a Deadline object with content and date and isDone.
     *
     * @param content String content.
     * @param date    LocalDateTime due date.
     * @param isDone  Boolean to show if the task is done.
     * @param tags    ArrayList of Tags.
     * @param ui      Ui UI object.
     */
    public Deadline(String content, LocalDateTime date, boolean isDone, ArrayList<Tag> tags, Ui ui) {
        super(content, date, isDone, tags, ui);
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
                    .format(Ui.OUTPUT_FORMATTER) + ")" + tags.toString();
        } else {
            return "[D][ ] " + getContent() + " (by: " + date
                    .format(Ui.OUTPUT_FORMATTER) + ")" + tags.toString();
        }
    }
}
