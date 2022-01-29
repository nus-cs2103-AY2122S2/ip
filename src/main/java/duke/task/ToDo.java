package duke.task;

/**
 * One of the three tasks that a user can indicate.
 * Indicate something that the user has todo.
 *
 * @author Justin Ng Jie Ern
 */
public class ToDo extends Task{
    /**
     * Constructor to create a ToDo Object.
     * @param name Name of the ToDo task.
     * @param isChecked Boolean to check if the task is marked or not
     * @param taskLabel "T" to represent ToDo.
     */
    public ToDo(String name, boolean isChecked, String taskLabel) {
        super(name, isChecked, taskLabel);
    }

    /**
     * Prints out information for ToDo task.
     *
     * @return String of ToDo task.
     */
    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + super.isTaskCheck() + "] " + super.toString();
    }
}
