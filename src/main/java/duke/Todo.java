package duke;

/**
 * Represents a task to do.
 */
public class Todo extends Task {
    /**
     * Class constructor.
     * The task generated is by default not done.
     *
     * @param content description of the task.
     */
    public Todo(String content) {
        super(content);
    }

    /**
     * Class Constructor.
     *
     * @param content description of the task.
     * @param isDone whether the task is done.
     */
    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }

    @Override
    public Todo mark(boolean isDone) {
        return new Todo(super.getContent(), isDone);
    }

    @Override
    public String fileFormat() {
        return "T" + super.fileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
