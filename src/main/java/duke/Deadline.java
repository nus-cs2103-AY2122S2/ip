package duke;

/**
 * This is a type of duke.Task: deadline
 *
 */
public class Deadline extends Task {

    /**
     * Constructor of deadline.
     * Have to strip/process the input first and remove 'deadline'
     *
     * @param input descriptor of task
     * @param number task number
     * @param deadline deadline to complete task
     * @param isReading flag to check if input is being read from file data
     */
    public Deadline(String input, int number, String deadline, boolean isReading) {
        super(input, number, deadline, "D", isReading);
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        String s = String.format("%d. [D][%s] %s (by: %s)\n", number + 1, getStatus(), name, time);
        return s;
    }
}
