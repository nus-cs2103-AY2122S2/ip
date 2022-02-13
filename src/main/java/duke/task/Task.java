package duke.task;

/**
 * Task class to store various types of tasks in chatbot
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Task)) {
            return false;
        }

        Task currTask = (Task) o;
        if (currTask.description.equals(this.description)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
