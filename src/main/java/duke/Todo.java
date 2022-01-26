package duke;

/**
 * Represents the todo tasks input by the user
 */
public class Todo extends Task {

    /**
     * Super constructor to the task class
     *
     * @param description the description of the todo task
     * @return
     * @throws
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns todo task in string format
     *
     * @param
     * @return todo task in a string format
     * @throws
     */
    public String getDescription() {
        String newReply = super.description.replace("todo ", "");
        return "[T]" + "[" + super.getStatusIcon() + "] " + newReply;
    }
}
