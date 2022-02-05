/**
 * Each task will hold the status of done or not done, plus description of the
 * task to be done.
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Call this method to see if we should mark X or leave blank.
     * 
     * @return
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    // Getters
    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    // Setters
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}