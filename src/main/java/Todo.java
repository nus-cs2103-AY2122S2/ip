public class Todo extends Task {

    /**
     * Constructor for Task class, set isDone to false by default
     * @param description Name of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
