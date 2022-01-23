package echo.task;

/**
 * This class inherits from Task class and encapsulates a todo task.
 */
public class TodoTask extends Task {

    /**
     * Constructor for TodoTask class.
     *
     * @param desc Description of TodoTask.
     */
    public TodoTask(String desc) {
        super(desc);
    }

    /**
     * String representation of TodoTask for saving.
     *
     * @return String representation of TodoTask for saving.
     */
    @Override
    public String saveFormat() {
        return "T | " + super.saveFormat();
    }

    /**
     * String representation of TodoTask.
     *
     * @return String representation of TodoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
