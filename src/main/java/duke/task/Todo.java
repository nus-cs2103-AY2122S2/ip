package duke.task;

/**
 * Todo represents a Task that a user must complete, but with no deadline.
 *
 * @author Jian Rong
 */
public class Todo extends Task {

    /**
     * Constructor of Todo Class.
     * @param title Title of Todo
     */
    public Todo(String title) {
        super(title);
        System.out.println("added: " + this.toString());
    }

    /**
     * Returns a summary of the Todo Task.
     * @return The summary of the Todo.
     */
    public String toString() {
        if (this.isChecked) {
            return "[T][X] " + this.title;
        } else {
            return "[T][ ] " + this.title;
        }
    }

}
