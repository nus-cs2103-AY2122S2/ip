package duke;

/**
 * Todo class to store todo type of task.
 */
public class Todo extends Task {

    protected String type;

    /**
     * Todo object constructor.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Returns a String of the description of the task.
     *
     * @return  description of the task
     */
    public String getDescription() {
        return super.description;
    }

    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Return description of the task.
     *
     * @return Description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
