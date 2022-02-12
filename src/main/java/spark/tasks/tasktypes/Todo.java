package spark.tasks.tasktypes;

/**
 * Represents a todo on the user's task list.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Generates a new ToDo and initialises it with the
     * given completion status.
     *
     */
    public Todo(boolean isDone, String title) {
        super(isDone, title);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns an encoded-representation of the Todo that can be
     * stored in a text-file and decoded into a Todo.
     *
     * @return a String containing the encoded-representation of the Todo.
     */
    @Override
    public String encodeTask() {
        return String.format("T @@@ %b @@@ %s", super.isDone(), super.getTitle());
    }
}
