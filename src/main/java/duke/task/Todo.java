package duke.task;

/**
 * This is a Task, specifically a Todo task.
 * It only accepts the details of the task.
 */
public class Todo extends Task {
    private String type;

    /**
     * Creates a Todo task that has a description.
     *
     * @param description Description of the task inputted by the user.
     */
    public Todo(String description) {
        super(description);
        type = "T";
    }

    /**
     * Constructs a string that is formatted for the user to view their
     * task description, task type, and task status.
     *
     * @return returns a formatted string that should be used when printing out the created Todo task.
     */
    @Override
    public String getTask() {
        return "[" + type +"]" + super.getTask();
    }

    /**
     * Constructs a string that is formatted to be printed into the txt file.
     * Consists of the task description and task type.
     *
     * @return returns a partially formatted string that should be used when saving a todo task to the text file
     * <type> | <description>
     */
    @Override
    public String getDescription() {
        return type + " | " + description;
    }
}