package jeff.task;

/**
 * Todo class is a task customised to store the descriptions of task to do.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param description Name of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the identity of this Task class.
     *
     * @return T for Event.
     */
    public String whatType() {
        return "T";
    }

    /**
     * toString method specific for Todo class,
     * inherits toString() fromTask class while adding additional information.
     * Like the type of task, [T].
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
