package duke.task;

public class Todo extends Task {
    /**
     * Instantiate Todo object
     *
     * @param name name of Todo
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Instantiate Todo object with status
     *
     * @param name name of Todo
     * @param done status of completion
     */
    public Todo(String name, Boolean done) {
        super(name, 'T', done);
    }
}
