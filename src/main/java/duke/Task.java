package duke;

/**
 * Abstracts a task.
 */
public abstract class Task {
    private final String content;
    private final boolean isDone;

    /**
     * Class constructor.
     * The task generated is by default not done.
     *
     * @param content description of the task.
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    /**
     * Class Constructor.
     *
     * @param content description of the task.
     * @param isDone whether the task is done.
     */
    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done or otherwise.
     *
     * @param isDone whether the task is done.
     * @return a marked task.
     */
    public abstract Task mark(boolean isDone);

    /**
     * Returns the string representation of the task in storage file.
     *
     * @return string representation of the task in storage file.
     */
    public String fileFormat() {
        if (isDone) {
            return " | 1 | " + content + " | ";
        } else {
            return " | 0 | " + content + " | ";
        }
    }

    public boolean match(String keyword) {
        return content.contains(keyword);
    }

    /**
     * Returns the description of the task.
     * @return description of the task.
     */
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        String s = "[";
        if (isDone) {
            s += "X] ";
        } else {
            s += " ] ";
        }
        return s + content;
    }
}
