package Duke.task;

import java.io.Serializable;

/**
 * a subclass of task
 */
public class ToDo extends Task implements Serializable {

    /**
     * Constructor
     * @param description task description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation
     * @return String representation of this task
     */
    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.getDescription();
    }
}
