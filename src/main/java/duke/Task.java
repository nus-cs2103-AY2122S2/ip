package duke;

/**
 * Represents a task
 */
abstract class Task {
    private final String task;
    private final String type;
    private boolean completed;

    public Task(String task, String type) {
        this.task = task;
        this.type = type;
        this.completed = false;
    }

    public Task(String task, String type, boolean completed) {
        this.task = task;
        this.type = type;
        this.completed = completed;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getTaskName() {
        return this.task;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void setUncompleted() {
        this.completed = false;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[x] " + task;
        } else {
            return "[ ] " + task;
        }
    }

}
