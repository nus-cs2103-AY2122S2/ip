package tasks;

/**
 * This class encapsulates a tasks.Task, which contains a String and a checkbox to indicate if it is done.
 *
 * @author Ong Han Yang
 */
public abstract class Task {
    /** The description of the task.*/
    private String desc;
    /** Whether the task is done or not.*/
    private boolean isDone;
    /** The Cross symbol used to show that a class is marked as done. Original: ✓ or \u2713*/
    private static String TICK = "X";

    /**
     * Constructor to create a task.
     * Abstract class Task so this should not be used normally.
     *
     * @param desc the description of the task.
     */
    protected Task(String desc) {
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

    /**
     * Overridden toString method to represent the task as a String.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? Task.TICK : " ", desc);
    }

}
