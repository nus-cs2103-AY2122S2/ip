package duke.task;

/**
 * Encapsulates behavior of Todo type of Task
 */
public class Todo extends Task {
    /**
     * Instantiates Todo object
     *
     * @param name name of Todo
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Instantiates Todo object with status
     *
     * @param name name of Todo
     * @param done status of completion
     */
    public Todo(String name, Boolean done) {
        super(name, 'T', done);
    }
}
