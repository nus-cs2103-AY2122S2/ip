package duke;

/**
 * Represents the Deadline Task object.
 */
public class Deadline extends duke.Task {

    /**
     * Initializes the Deadline object
     * @param description string which describes the task.
     */
    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, String tag) {
        super(description, tag);
    }

    public String toString() {
        if (tag != null){
            return String.format("[D] [%s] %s (%s)", getStatusIcon(), description, tag);
        } else {
            return String.format("[D] [%s] %s", getStatusIcon(), description);
        }

    }

}
