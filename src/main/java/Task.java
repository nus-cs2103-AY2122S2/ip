/**
 * This class encapsulates a Task, which contains a String and a checkbox to indicate if it is done.
 *
 * @author Ong Han Yang
 */
public class Task {
    /** The description of the task.*/
    private String desc;
    /** Whether the task is done or not.*/
    private boolean isDone;
    /** The Tick symbol used to show that a class is marked as done.*/
    private static String TICK = "\u2713";

    /**
     * Constructor to create a task.
     *
     * @param desc the description of the task.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Marks the task as done/undone.
     * @param isDone whether the task is done or not.
     */
    public void markAs(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? Task.TICK : " ", desc);
    }
}
