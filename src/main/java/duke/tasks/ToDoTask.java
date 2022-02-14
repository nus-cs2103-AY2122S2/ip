package duke.tasks;

/**
 * The ToDoTask class contains basic attributes
 * and behaviours of a todo task. It extends
 * from the Task class.
 *
 * @author  Melvin Chan Zijun
 */
public class ToDoTask extends Task {
    /**
     * Sole constructor.
     *
     * @param name name of task
     */
    public ToDoTask(String name) {
        super(name);
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the prefix of todo task.
     *
     * @return String prefix of todo task
     */
    @Override
    public String getPrefix() {
        return "T";
    }

    /**
     * Overrides the abstract method of its parent class.
     *
     * @return String always returns "00000000"
     */
    @Override
    public String getDate() {
        return "00000000";
    }

    /**
     * Overrides the abstract method of its parent class.
     *
     * @return String always returns "0000
     */
    @Override
    public String getTime() {
        return "0000";
    }

    /**
     * Overrides the toString() method of the parent.
     *
     * @return String todo task in String form
     */
    @Override
    public String toString() {
        String prefix = "[T]";
        return prefix + super.toString();
    }
}
