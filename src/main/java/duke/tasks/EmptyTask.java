package duke.tasks;

/**
 * The EmptyTask class contains basic attributes
 * and behaviours of an empty task. It extends
 * from the Task class. This is returned in cases
 * where a Task is not created due to DukeException.
 *
 * @author  Melvin Chan Zijun
 */
public class EmptyTask extends Task {
    /**
     * Sole constructor.
     */
    public EmptyTask() {
        super("EMPTY");
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the prefix of empty task.
     *
     * @return String prefix of empty task
     */
    @Override
    public String getPrefix() {
        return "";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the date of empty task.
     *
     * @return String date of the empty task
     */
    @Override
    public String getDate() {
        return "";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the time of empty task.
     *
     * @return String time of this empty task
     */
    @Override
    public String getTime() {
        return "";
    }

    /**
     * Overrides method of its parent class.
     * Always returns true.
     *
     * @return boolean true
     */
    @Override
    public boolean isEmptyTask() {
        return true;
    }
}
