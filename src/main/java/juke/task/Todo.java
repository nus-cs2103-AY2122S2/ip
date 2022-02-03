package juke.task;

/**
 * Basic task with a description.
 */
public class Todo extends Task {
    /**
     * Construtor that initializes task with description.
     *
     * @param description Description.
     */
    public Todo(String description) {
        super(description);
    }
    
    /**
     * Returns the task icon.
     *
     * @return Task icon.
     */
    @Override
    public String getTaskIcon() {
        return TaskType.TODO.getTaskIcon();
    }
}
