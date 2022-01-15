public class Todo extends Task {

    /**
     * Constructor of Todo Class
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the task in proper format.
     * @return String of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
