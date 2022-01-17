public class ToDoTask extends Task {
    /**
     * Constructor for a todo task.
     * @param name name of the task
     */
    public ToDoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
