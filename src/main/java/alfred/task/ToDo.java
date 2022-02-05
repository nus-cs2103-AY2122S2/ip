package alfred.task;

import java.time.LocalDateTime;

/**
 * Encapsulates a T0D0 task.
 */
public class ToDo extends Task {
    protected final static String type = "T";

    /**
     * Constructs a T0D0 object.
     *
     * @param description Name of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    protected ToDo(Boolean marked, String description) {
        super(description);
        if (marked) {
            this.markComplete();
        }
    }

    @Override
    public String taskToSaveString() {
        String mark = this.isCompleted ? Task.COMPLETION_MARK : Task.INCOMPLETE_MARK;
        return String.join(Task.FORMAT_SPLIT, this.type, mark, this.description);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }

}
