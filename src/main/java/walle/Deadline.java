package walle;

/**
 * This is a type of Task: deadline
 *
 */
public class Deadline extends Task {

    /**
     * Constructor of deadline.
     * Have to strip/process the input first and remove 'deadline'
     *
     * @param input descriptor of task
     * @param deadline deadline to complete task
     * @param taskNum number representing the task in the list
     * @param isReading flag to check if input is being read from file data
     */
    public Deadline(String input, String deadline, int taskNum, boolean isReading) {
        super(input, taskNum, deadline, "D", isReading);
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        assert (name != "" || name != null) : "Invalid: Deadline has no name";
        assert (time != "" || time != null) : "Invalid: Deadline has no time";
        assert (number > 0) : "Invalid: taskNumber is negative";
        String s = String.format("%d. [D][%s] %s (by: %s)\n", number + 1, getStatus(), name, time);
        return s;
    }
}
