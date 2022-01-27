package duke.task;

/**
 * A task object that only has content and isDone.
 * No date or time is specified.
 */
public class ToDo extends Task {

    /**
     * Instantiates ToDo object with given content.
     * @param content Content for ToDo.
     */
    public ToDo(String content) {
        super(content);
    }

    /**
     * Instantiates ToDo object with content and whether it's marked done.
     * @param content String content for ToDo.
     * @param isDone Boolean to show if the task is done.
     */
    public ToDo(String content, boolean isDone) {
        super(content, isDone);
    }

    /**
     * Returns the String representation of this class object.
     *
     * @return String String representation of ToDo.
     */
    @Override
    public String toString() {
        if (getIsDone()) {
            return "[T][X] " + getContent();
        } else {
            return "[T][ ] " + getContent();
        }
    }
}
