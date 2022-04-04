package duke;

/**
 * Class for To-do , which extends the Task class
 *
 * @author Benjamin Koh
 */

public class Todo extends Task {

    /**
     * Constructor for a new instance of To-do, which entails the name of the To-do, and the Date & Time of the To-do
     *
     * @param taskName Name of the deadline
     */

    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Return a string with the Task type, the isDone status, and the To-do name
     *
     * @return a string with the Task type, the isDone status, and the To-do name
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
