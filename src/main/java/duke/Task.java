package duke;

public abstract class Task {
    private final String description;
    private boolean completed;

    Task(String description) {
        this.description = description;
        this.completed = false;
    }

    Task(String description, Boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public String getType() {
        return "Task";
    }

    @Override
    public String toString() {
        String taskString = "";
        if (this.completed) {
            taskString += "[X] ";
        } else {
            taskString += "[ ] ";
        }
        taskString += this.description;
        return taskString;
    }
}
