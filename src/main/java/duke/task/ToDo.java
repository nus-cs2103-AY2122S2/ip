package duke.task;

/**
 * Represents a ToDo Task.
 */

public class ToDo extends Task implements Comparable<Task> {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Compares this and other task by the task type.
     * @param otherTask The other task that is compared with.
     * @return Returns -1,0,1.
     */
    @Override
    public int compareTo(Task otherTask) {
        int compareValueOfOtherTask = 0;
        int compareValueOfCurrentTask = 1;
        if (otherTask instanceof ToDo) {
            compareValueOfOtherTask = 1;
        }
        if (otherTask instanceof Deadline) {
            compareValueOfOtherTask = 2;
        }
        if (otherTask instanceof Event) {
            compareValueOfOtherTask = 3;
        }
        return Integer.compare(compareValueOfCurrentTask, compareValueOfOtherTask);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
