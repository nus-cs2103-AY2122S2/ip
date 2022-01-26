package duke.task;

public class Todo extends Task {

    /**
     * Constructor of Todo Class
     *
     * @param description The description of the todo.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        if (isDone) {
            this.markAsDone();
        }
    }

    @Override
    public String writeToFile() {
        return " T " + super.writeToFile();
    }

    /**
     * Adds extra custom formatting for user view specific to todos.
     *
     * @return A custom String display of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
