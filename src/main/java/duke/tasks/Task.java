package duke.tasks;

/**
 * Task Creation
 *
 * @author Justin Ng Jie Ern
 */
public class Task {
    /**
     * Name of the Task.
     */
    private String name;

    /**
     * Boolean on whether the Task is marked or not.
     */
    private boolean isChecked;

    /**
     * TaskLabel to indicate if Task is a Deadline, Event or Todo.
     */
    private String taskLabel;

    /**
     * Constructor for a Task.
     *
     * @param name Name of the Task.
     * @param isChecked Boolean on whether the Task is marked or not.
     * @param taskLabel TaskLabel to indicate if Task is a Deadline, Event or Todo.
     */
    public Task(String name, boolean isChecked, String taskLabel) {
        this.name = name;
        this.isChecked = isChecked;
        this.taskLabel = taskLabel;
    }

    /**
     * Prints out the name of Task.
     *
     * @return String of the Task name.
     */
    @Override
    public String toString() {

        return this.name;
    }

    /**
     * Getter for boolean on whether task is marked or not.
     * @return Boolean of isChecked
     */
    public boolean getChecked() {
        return this.isChecked;
    }

    /**
     * Setter for boolean on whether task is marked or not.
     *
     * @param check Boolean to be set.
     */
    public void setChecked(boolean check) {
        this.isChecked = check;
    }

    /**
     * Getter for Task Label.
     *
     * @return String to indicate which Task is it.
     */
    public String getTaskLabel() {
        return this.taskLabel;
    }

    /**
     * String on whether that task is marked or not.
     *
     * @return "X" if the task is marked. " " if the task is unmarked.
     */
    public String isTaskCheck() {
        return this.isChecked ? "X" : " ";
    }
}
