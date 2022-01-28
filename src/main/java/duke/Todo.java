package duke;

/**
 * Represent a todo task
 * It corresponds to a task todo with a string of task and the time
 */
public class Todo extends Task {

    /**
     * Constructor of Todo
     * @param task description of task
     */
    Todo(String task) {
        super(task, "T");
    }
}
