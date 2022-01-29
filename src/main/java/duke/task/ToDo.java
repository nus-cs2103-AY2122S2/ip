package duke.task;

/**
 * ToDo class
 */
public class ToDo extends Task {

    private boolean done;
    private String description;

    /**
     * Constructor for ToDo object
     * @param description description of task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Override toString function to display task in required syntax
     * @return String in required syntax
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
