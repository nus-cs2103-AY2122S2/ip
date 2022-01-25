package duke.task;

public class Task {
    private String description;
    private boolean completed;
    private char type;

    public Task(String description, char type) {
        this.description = description;
        this.completed = false;
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getStatusIcon() {
        return (this.completed) ? "X" : " ";
    }

    public char getType() {
        return this.type;
    }

    public void setCompleted(boolean setCompleted) {
        this.completed = setCompleted;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
