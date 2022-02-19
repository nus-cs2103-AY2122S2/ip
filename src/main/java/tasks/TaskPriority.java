package tasks;

/**
 * Represents the importance of a task.
 */
public enum TaskPriority {
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String name;

    TaskPriority(String name) {
        this.name = name;
    }

    /**
     * Provides a string representation of a task priority.
     *
     * @return the string representation of a task priority.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
