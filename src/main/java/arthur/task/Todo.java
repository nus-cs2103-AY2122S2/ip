package arthur.task;

/**
 * A class that creates Task.Todo objects
 */
public class Todo extends Task {

    /**
     * Constructor for Todo objects
     * @param e String to be created as Task object
     */
    public Todo(String e) {
        super(e);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
