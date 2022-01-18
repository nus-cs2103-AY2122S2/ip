import java.util.*;

/**
 * This class encapsulates a task input by the user.
 */
public class Task {
    private String description; //description of the task.
    private boolean isDone; //boolean condition: when true, task is done. when false, task is not done.
    /**
     *
     * Constructor to create a task object.
     * @param description: Description of task with String datatype.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method mark the task as done.
     * @return: returns a String that verifies that the task is marked as done.
     */
    public String markDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n" + this.toString();
    }

    /**
     * This method unmark the task as done.
     * @return: returns a String that verifies that the task is marked as undone.
     */
    public String unmarkDone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n" + this.toString();
    }

    /**
     *
     * @return: Returns a String of the description of the task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
