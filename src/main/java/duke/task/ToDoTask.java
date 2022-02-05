package duke.task;

/**
 * Represents a simple todo task with only a description.
 */

public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    /**
     * Returns a formatted string including the type indicating it is a todo.
     *
     * @return
     */

    public String toString() {
        String prefix = "[T]";
        String stateAndName = super.toString();
        return prefix + stateAndName;
    }
}
