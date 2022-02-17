package ann.data.tasks;

/**
 * Represents the type of task.
 *
 * @author Hong Anh
 * @version 0.1
 */
public enum TaskType {
    TODO("todo"), EVENT("event"), DEADLINE("deadline");
    private final String keyword;

    /**
     * Creates a new TaskType object from the specified task type name.
     *
     * @param taskName the name of the task type.
     */
    TaskType(String taskName) {
        this.keyword = taskName;
    }
    public String getKeyword() {
        return keyword;
    }
}
