package duke.task;

/**
 * Represents a task which is of type todo.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Task description that is formatted to be written into the file.
     * @return Task description format for file input.
     */
    @Override
    public String taskDescriptionForFile() {
        int i = super.isDone ? 1 : 0;
        return "T , " + i + " , " + this.description.trim();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
