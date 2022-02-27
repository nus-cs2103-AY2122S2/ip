package arthur.task;

/**
 * A class that creates Task.Todo objects.
 */
public class Todo extends Task {
    private static final String LOGO = "[T]";

    /**
     * Constructs Todo objects.
     *
     * @param e String to be created as Task object.
     */
    public Todo(String e) {
        super(e);
    }

    /**
     * Formats the string representation of the todo object.
     *
     * @return A formatted string of the logo and the description.
     */
    @Override
    public String toString() {
        return LOGO + super.toString();
    }
}
