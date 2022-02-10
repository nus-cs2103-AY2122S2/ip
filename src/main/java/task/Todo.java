package task;

/**
 * The Todo class is a type of Task which is used to represent todo.
 */
public class Todo extends Task implements Comparable<Task> {
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
