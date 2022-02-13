package juke.task;

/**
 * Basic task with a description.
 */
public class Todo extends Task {
    /**
     * Constructor that initializes task with description.
     *
     * @param description Description.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
        assert getTaskIcon() == TaskType.TODO.getTaskIcon();
    }
}
