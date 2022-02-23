package duke.tasks;

/**
 * A type of task.
 * The most generic type of task and is symbolized with "[T]".
 */
public class Todo extends WordListItem {
    static private final String SYMBOL = "[T]";

    /**
     * Constructor for todo.
     * @param description description of the event
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Get the symbol for the todo.
     * @return symbol
     */
    static public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
