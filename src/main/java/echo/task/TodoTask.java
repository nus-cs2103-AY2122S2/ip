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
     * Returns string representation of TodoTask for saving.
     *
     * @return String representation of TodoTask for saving.
     */
    @Override
    public String saveFormat() {
        return "T | " + super.saveFormat();
    }

    /**
     * Returns string representation of TodoTask.
     *
     * @return String representation of TodoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks if instances of TodoTask are equal.
     *
     * @param obj Object.
     *
     * @return If the DESCRIPTION is equal, returns true; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoTask) {
            // Since obj is an instanceof TodoTask, it is safe to type cast
            // Object to TodoTask.
            TodoTask todoTask = (TodoTask) obj;
            return super.equals(todoTask);
        }
        return false;
    }
}
