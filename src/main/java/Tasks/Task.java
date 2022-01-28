package Tasks;

/**
 * Task is a class that contains the details of a task
 */
public class Task {
    private boolean completed;
    private String type;
    private String description;

    /**
     * Constructor of the Task class
     * @param description Description about the task
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    // Overloaded constructor
    public Task(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public void toggleCompleted() {
        this.completed = true;
    }

    public void toggleUncompleted() {
        this.completed = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    /**
     * Generates a String representation of the Task object
     * @return String representation of the task object
     */
    @Override
    public String toString() {

        if (this.completed) {
            return "[" + type + "]" + "[X] " + description;
        } else {
            return "[" + type + "]" + "[ ] " + description;
        }
    }

    /**
     * Generates the String to be stored inside the database
     * @return
     */
    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        return String.format("T|%s|%s", complete, this.getDescription());
    }
}
