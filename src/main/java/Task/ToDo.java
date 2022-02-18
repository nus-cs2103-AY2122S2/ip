package task;

public class ToDo extends Task {

    /**
     * Default constructor for ToDo object.
     *
     * @param description description of ToDo to be added
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns type of Task as a String.
     *
     * @return String form of Task's type
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Prints ToDo details as a String.
     *
     * @return String form of ToDo details
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}