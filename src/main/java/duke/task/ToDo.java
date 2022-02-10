package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;

/**
 * A task object that only has content and isDone.
 * No date or time is specified.
 */
public class ToDo extends Task {

    /**
     * Instantiates ToDo object with given content.
     * @param content Content for ToDo.
     */
    public ToDo(String content, Ui ui) {
        super(content, ui);
    }

    /**
     * Instantiates ToDo object with content and whether it's marked done.
     * @param content String content for ToDo.
     * @param isDone Boolean to show if the task is done.
     */
    public ToDo(String content, boolean isDone, Ui ui) {
        super(content, isDone, ui);
    }

    public ToDo(String content, boolean isDone, ArrayList<Tag> tags, Ui ui) {
        super(content, isDone, tags, ui);
    }

    /**
     * Returns the String representation of this class object.
     *
     * @return String String representation of ToDo.
     */
    @Override
    public String toString() {
        if (getIsDone()) {
            return "[T][X] " + getContent() + tags.toString();
        } else {
            return "[T][ ] " + getContent() + tags.toString();
        }
    }
}
