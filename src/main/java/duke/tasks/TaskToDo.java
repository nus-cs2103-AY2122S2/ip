package duke.tasks;

/**
 * The TaskToDo class contains basic attributes
 * and behaviours of a ToDo Task. It extends
 * from the Task class.
 *
 * @author  Melvin Chan Zijun
 */
public class TaskToDo extends Task {
    /**
     * Sole constructor.
     *
     * @param name - name of task
     */
    public TaskToDo(String name) {
        super(name);
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the prefix of TaskToDo.
     *
     * @return String - prefix of the TaskToDo
     */
    @Override
    public String getPrefix() {
        return "T";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Always returns "00000000".
     *
     * @return String - "00000000"
     */
    @Override
    public String getDate() {
        return "00000000";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Always returns "0000".
     *
     * @return String - "0000"
     */
    @Override
    public String getTime() {
        return "0000";
    }

    /**
     * Overrides the toString() method of the parent.
     *
     * @return String - String of this TaskToDo
     */
    @Override
    public String toString() {
        String prefix = "[T]";
        return prefix + super.toString();
    }
}
