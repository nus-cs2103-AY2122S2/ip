public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class, set isDone to false by default
     * @param description Name of the to-do task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description; // Returns the name of the to-do
    }

    public void setMark() {
        isDone = true; // Set the value of isDone to true
    }

    public void setUnmark() {
        isDone = false; // Set the value of isDone to false
    }

}
