package butler.task;

public class ToDo extends Task {

    /**
     * Constructs a ToDo object with a provided description.
     *
     * @param description description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns symbolic representation of task type.
     *
     * @return String Representation of task type.
     */
    public String getTaskType() {
        return "[T]";
    }

    /**
     * Represents ToDo object as a String
     *
     * @return String Representation of task as a whole.
     */
    @Override
    public String toString() {
        return this.getTaskType() + super.toString();
    }
}
