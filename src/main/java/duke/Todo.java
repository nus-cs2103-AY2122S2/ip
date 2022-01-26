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

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
