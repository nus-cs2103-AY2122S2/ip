package walle;

/**
 * This is a type of Task: todo
 *
 */
public class ToDo extends Task {
    /**
     * Constructor of Todo.
     *
     * @param input descriptor of task
     * @param taskNum number representing the task in the list
     * @param isReading flag to check if input is being read from file data
     */
    public ToDo(String input, int taskNum, boolean isReading) {
        super(input, taskNum, null, "T", isReading);
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        assert (name != "" || name != null) : "Invalid: Deadline has no name";
        String s = String.format("%d. [T][%s] %s\n", number + 1, getStatus(), name);
        return s;
    }
}
