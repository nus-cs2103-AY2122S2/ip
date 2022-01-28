package myboss;

/**
 * Represents a task of type T.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo task Object of the specified name.
     *
     * @param taskName name of task.
     */
    public ToDo(String taskName) {
        super(taskName, "T");
    }

    /**
     * Creates a ToDo task Object of the specified name and whether task is done.
     *
     * @param taskName name of task.
     * @param isDone whether task is done.
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, "T", isDone);
    }

    /**
     * Returns string output of a ToDo Object when marked as done or not done.
     *
     * @param isDone whether task is to be marked as done or not done.
     * @return string output of a ToDo Object when marked as done or not done.
     */
    @Override
    public String markAsDone(boolean isDone) {
        if (isDone) {
            this.isDone = true;
            return  " Nice! I've marked this task as done:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (this.isDone ? "X" : " ") + "] " + // [X]
                    this.taskName;
        } else {
            this.isDone = false;
            return "OK, I've marked this task as not done yet:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (this.isDone ? "X" : " ") + "] " + // [X]
                    this.taskName;
        }
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.taskName + "\n";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
