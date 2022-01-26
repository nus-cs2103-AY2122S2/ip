package yale.task;

/**
 * Generic Task class
 */
public class Task {
    /**
     * String name to describe the task
     */
    protected String name;
    /**
     * Boolean isMarked to determine
     * if tasked is marked
     */
    protected boolean isMarked = false;

    /**
     * Constructor method
     * @param name
     * @param isMarked
     */
    public Task(String name, boolean isMarked) {
        this.name = name;
        this.isMarked = isMarked;

    }

    /**
     * Returns a String to indicate
     * to user if task is marked or not
     * @return
     */
    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    /**
     * Marks a Task item as done
     */
    public void markTask() {
        isMarked = true;
    }

    /**
     * Unmarks a Task item
     */
    public void unmarkTask() {
        isMarked = false;
    }

    public String export() {
        return " " + "| "
                + (isMarked? 1 : 0) + " | " + this.name;
    }

    /**
     * Customised toString() method
     * @return String displaying
     * whether the task is marked along with
     * its name
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.name;
    }
}
