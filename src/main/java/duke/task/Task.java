package duke.task;

import java.util.Optional;

public class Task {

    private String task;
    private Boolean status;

    public Task(String task) {
        this.task = task;
        this.status = false;
    }

    public Task(String task, boolean status) {
        this.task = task;
        this.status = status;
    }

    public String getTaskType() {
        return "task";
    }

    public String getTask() {
        return task;
    }

    public Optional<String> getTime() {
        return Optional.empty();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override public String toString() {
        return "[" + iconToString() + "] " + this.task;
    }

    public Character iconToString() {
        if (this.status) {
            return '\u2705';
        } else {
            return '\u274c';
        }
    }
}
