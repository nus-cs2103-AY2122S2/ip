package duke;

/**
 * Class {@code Todo}
 * Extends {@code Task}
 */
public class Todo extends Task {

    /**
     * Constructor for {@code Todo}
     *
     * @param description Description of the Todo Task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns Status Icon of current task as a String representation
     *
     * @return String representation of current task status icon
     */
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

}
