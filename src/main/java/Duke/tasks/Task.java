package Duke.tasks;
public class Task {
    protected String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    void done() {
        this.isDone = true;
    }

    void undone() {
        this.isDone = false;
    }

    String finished() {
        if(this.isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
