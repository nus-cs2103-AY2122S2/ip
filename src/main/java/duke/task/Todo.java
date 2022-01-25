package duke.task;

/**
 * Represents a todo.
 */
public class Todo extends Task {
    /**
     * Creates a todo object using a given description.
     *
     * @param description the description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getAppendData() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + description ;
    }

    @Override
    public boolean isHasDate() {
        return false;
    }

    @Override
    public boolean isHasTime() {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
