package duke.tasks;

public class Task {

    protected String description;
    protected boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public void setIncomplete() {
        this.isComplete = false;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    @Override
    public String toString() {
        return isComplete
                ? "[X] " + description
                : "[ ] " + description;
    }
}
