package duke.task;

/**
 * Represents a task which is of type todo.
 */
public class ToDo extends Task {

    private String type;

    /**
     * Constructor for this class.
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description);
        this.type = "todo";
    }

    /**
     * Task description that is formatted to be written into the file.
     *
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

    @Override
    public String getType() {
        return type;
    }
}
