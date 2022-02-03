
package task;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * marks Task as done
     */

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * marks Task as undone
     */

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * converts task to String form to be stored in text file
     * @return task in string form
     */

    abstract public String storageString();
}