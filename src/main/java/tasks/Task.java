package tasks;

/**
 * This class encapsulates a task, which contains a String and a checkbox to indicate if it is done.
 *
 * @author Ong Han Yang
 */
public abstract class Task {
    /** The Cross symbol used to show that a class is marked as done. Original: ✓ or \u2713 */
    private static final String TICK = "X";

    /** Whether the task is done or not.*/
    private boolean isDone;

    /** The description of the task.*/
    private String desc;

    /**
     * Constructs a task.
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
     *
     * @param isDone whether the task is done or not.
     */
    public void markAs(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the state of the task, if it is done or not.
     *
     * @return boolean value indicating if the task is done or not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Represents the task as a String.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? Task.TICK : " ", desc);
    }

}
