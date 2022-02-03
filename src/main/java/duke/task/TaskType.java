package duke.task;

import java.util.Arrays;

/**
 * Represents a type of task that can be created in the task list.
 */
public enum TaskType {
    /** A todo type task. */
    TODO("T", 1),
    /** A deadline type task. */
    DEADLINE("D", 2),
    /** An event type task. */
    EVENT("E", 3);

    private final String shorthand;
    private final int typeId;

    TaskType(String shorthand, int typeId) {
        this.shorthand = shorthand;
        this.typeId = typeId;
    }

    /**
     * Returns a single alphabet that represents the type of task.
     *
     * @return String containing a single alphabet representing the type of task.
     */
    public String getShorthand() {
        return this.shorthand;
    }

    /**
     * Returns a numeric identifier for the type of task.
     *
     * @return Integer associated with the type of task.
     */
    public int getTypeId() {
        return this.typeId;
    }

    /**
     * Returns the {@link TaskType} with the supplied numeric identifier.
     *
     * @param typeId Numeric identifier of the type of task.
     * @return The matching {@link TaskType}, or null if the provided identifier is invalid.
     */
    public static TaskType matchType(int typeId) {
        return Arrays.stream(TaskType.values()).filter(x -> x.getTypeId() == typeId)
                .findFirst()
                .orElse(null);
    }
}
