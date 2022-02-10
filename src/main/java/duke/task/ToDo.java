package duke.task;
import java.util.ArrayList;

import duke.ui.Ui;


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

    /**
     * Instantiates a ToDo object with content and date and isDone.
     *
     * @param content String content.
     * @param isDone  Boolean to show if the task is done.
     * @param tags    ArrayList of Tags.
     * @param ui      Ui UI object.
     */
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
