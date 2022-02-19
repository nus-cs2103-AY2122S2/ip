package tasks;

/**
 * Represents the importance of a task.
 */
public enum TaskPriority {
    LOW(TaskPriority.LOW_PRIORITY_LABEL),
    MEDIUM(TaskPriority.MEDIUM_PRIORITY_LABEL),
    HIGH(TaskPriority.HIGH_PRIORITY_LABEL);

    private static final String LOW_PRIORITY_LABEL = "LOW";
    private static final String MEDIUM_PRIORITY_LABEL = "MEDIUM";
    private static final String HIGH_PRIORITY_LABEL = "HIGH";

    private final String label;

    TaskPriority(String label) {
        this.label = label;
    }

    /**
     * Converts a string to a task priority object.
     * Valid strings include "LOW", "MEDIUM", and "HIGH".
     *
     * @param value the string to be converted to a task priority.
     * @return A task priority object that corresponds to the given value.
     * @throws IllegalArgumentException If a value with no corresponding task priority is given.
     */
    public static TaskPriority parsePriority(String value) throws IllegalArgumentException {
        switch (value.trim()) {
        case TaskPriority.LOW_PRIORITY_LABEL:
            return LOW;
        case TaskPriority.MEDIUM_PRIORITY_LABEL:
            return MEDIUM;
        case TaskPriority.HIGH_PRIORITY_LABEL:
            return HIGH;
        default:
            throw new IllegalArgumentException("Given string cannot be converted to a TaskPriority");
        }
    }

    /**
     * Provides a string representation of a task priority.
     *
     * @return the string representation of a task priority.
     */
    @Override
    public String toString() {
        return this.label;
    }
}
