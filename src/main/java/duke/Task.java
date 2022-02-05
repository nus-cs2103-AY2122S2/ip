package duke;

/**
 * Represents tasks that user enters into the app
 */
public class Task {

    static final int COMPLETED_INDEX = 1;
    static final int DESCRIPTION_INDEX = 2;

    /**
     * String description of task to be completed
     */
    protected String description;
    /**
     * Boolean to indicate if class is completed
     */
    protected Boolean isCompleted = false;

    /**
     * Creation of new task
     *
     * @param description description of task to be completed
     */
    Task(String description) {
        this.description = description;
    }

    /**
     * Marks task as completed
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks task as incompleted
     */
    public void markIncompleted() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "[" + (this.isCompleted ? "x" : " ") + "] " + this.description + "\n";
    }

    /**
     * Returns String array of details of task
     * Index 1: Completed
     * Index 2: Description
     *
     * @return String array of details
     */
    public String[] getDetails() {
        String[] details = new String[4];
        details[COMPLETED_INDEX] = isCompleted ? "1" : "0";
        details[DESCRIPTION_INDEX] = description;
        return details;

    }
}
