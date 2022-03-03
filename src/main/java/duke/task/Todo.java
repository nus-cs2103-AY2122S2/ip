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
    }

    /**
     * Returns a summary of the Todo Task.
     * @return The summary of the Todo.
     */
    public String toString() {
        String result = "";
        switch (this.priority) {
            case LOW:
                result = "[L]";
                break;
            case MEDIUM:
                result = "[M]";
                break;
            case HIGH:
                result = "[H]";
                break;
        }
        if (this.isChecked) {
            result += "[T][X] ";
        } else {
            result += "[T][ ] ";
        }

        return (result + this.title);
    }

}
