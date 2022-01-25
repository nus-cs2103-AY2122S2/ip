package yalebot;


/**
 * Generic Task class
 */
public class Task {
    /**
     * String name to describe the task
     */
    private String name;
    /**
     * Boolean isMarked to determine
     * if tasked is marked
     */
    private boolean isMarked = false;

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
    public void markItem() {
        isMarked = true;
        System.out.println("Nice! I've marked this task as done:\n"
                + "  " + getStatusIcon() + " " + name);
    }

    /**
     * Unmarks a Task item
     */
    public void unmarkItem() {
        isMarked = false;
        System.out.println("OK, I've marked this task as not done yet:\n"
                + "  " + getStatusIcon() + " " + name);
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
