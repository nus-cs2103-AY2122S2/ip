package duke.task;

import java.util.Objects;

/**
 * The class for Todo object.
 */
public class Todo extends Task {

    public Todo(String t) {
        super(t);
    }

    public Todo(String t, String status) {
        super(t, Objects.equals(status, "1") ? true : false);
    }

    /**
     * Get the string for todo task
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskType() {
        return "T";
    }

}