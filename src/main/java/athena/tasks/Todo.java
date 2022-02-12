package athena.tasks;

import java.time.LocalDate;

/**
 * Represents a single todo item in a task list.
 */
public class Todo extends Task {
    /**
     * Constructs a new todo object with the given name.
     *
     * @param name The name of the todo object.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * @inheritDoc
     */
    public boolean isFallingBetweenInclusive(LocalDate startDate, LocalDate endDate) {
        return false; // because todo has no date-time information
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getIcon() {
        return "T";
    }
}
