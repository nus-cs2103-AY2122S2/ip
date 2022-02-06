package duke.task;

public class ToDo extends Task {

    /**
     * Instantiates a new ToDo.
     *
     * @param taskName the task name/description
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Instantiates a new ToDo.
     *
     * @param taskName the task name/description
     * @param isMarked the task has been marked/completed
     */
    public ToDo(String taskName, String isMarked) {
        super(taskName);
        this.isMarked = (isMarked.equals("1"));
    }

    /**
     * Returns the String representation of a `ToDo`.
     *
     * @return the String representation of a `ToDo`
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns the String representation of a `ToDo` that is written to the local data file.
     *
     * @return the String representation of a `ToDo` that is written to the local data file
     */
    public String toFile() {
        String markStatus = super.isMarked ? "1" : "0";
        return String.format("%s | %s | %s\n", "T", markStatus, super.taskName);
    }
}
