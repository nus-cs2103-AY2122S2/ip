package duke.tasks;

/**
 * The TaskEmpty class contains basic attributes
 * and behaviours of an TaskEmpty. It extends
 * from the Task class. This is returned in cases
 * where a Task is not created due to DukeException.
 *
 * @author  Melvin Chan Zijun
 */
public class TaskEmpty extends Task {
    /**
     * Sole constructor.
     */
    public TaskEmpty() {
        super("EMPTY");
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the prefix of TaskEmpty.
     *
     * @return String - prefix of the TaskEmpty
     */
    @Override
    public String getPrefix() {
        return "";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the date of TaskEmpty.
     *
     * @return String - date of the TaskEmpty
     */
    @Override
    public String getDate() {
        return "";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the time of TaskEmpty.
     *
     * @return String - time of this TaskEmpty
     */
    @Override
    public String getTime() {
        return "";
    }

    /**
     * Overrides method of its parent class.
     * Always returns true.
     *
     * @return boolean - true
     */
    @Override
    public boolean isEmptyTask() {
        return true;
    }
}
