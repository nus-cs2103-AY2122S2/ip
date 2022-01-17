import java.util.*;

/**
 * This class encapsulates a task input by the user.
 */
public class Task {
    private String description;

    /**
     *
     * Constructor to create a task object.
     * @param description: Description of task with String datatype.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     *
     * @return: Returns a String of the description of the task
     */
    @Override
    public String toString() {
        return this.description;
    }
}
