public abstract class Task {
    private final String description;
    private boolean completed;

    Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
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