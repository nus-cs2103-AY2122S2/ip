package task;

/**
 * The Todo class is a type of Task which is used to represent todo.
 */
public class Todo extends Task implements Comparable<Task> {
    /**
     * Constructs a Todo object.
     *
     * @param description the description of this todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this todo.
     *
     * @return the string representation of this todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the save format of this task.
     *
     * @return A String representing the save format of this task.
     */
    @Override
    public String getSaveFormat() {
        return "T," + ((isDone ? "1" : "0")) + "," + super.getSaveFormat();
    }

    /**
     * Compares 2 Tasks object and returns the order of the current Task
     * based on its type. If the other Task is an Todo,
     * it compares based on its description in ascending order
     *
     * @param o The other Task object
     * @return The order
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline || o instanceof Event) {
            return 1;
        } else if (o instanceof Todo) {
            return this.description.compareTo(o.description);
        }
        return 0;
    }
}
