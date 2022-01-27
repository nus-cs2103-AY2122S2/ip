package duke.task;

/**
 * Represents a task. A <code>ToDo</code> object corresponds to the task represented by
 * a String name.
 */
public class ToDo extends duke.task.Task {

    /**
     * Returns a new instance of a <code>ToDo</code> object with the specified name.
     * @param name Name of the deadline task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a String representation of the <code>ToDo</code> object to be read by the users in <code>Duke</code>.
     * @return User-friendly string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String representation of the <code>ToDo</code> object to be saved in the hard drive for future
     * uses. It is more concise and computer-friendly than the <code>toString</code> method.
     * @return Computer-friendly string representation of the todo for storing of data.
     */
    @Override
    public String toText() {
        return "T | " + (this.getIsDone() ? 1 : 0) + " | " + this.getName() + "\n";
    }
}
