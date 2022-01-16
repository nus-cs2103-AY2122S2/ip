/**
 * Todo task which inherits from Task class.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param desc Description of Todo task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * String representation of Todo task.
     *
     * @return String representation of Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
