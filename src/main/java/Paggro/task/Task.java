public abstract class Task {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    TaskType type;
    String description;
    boolean isDone;

    public Task(String des, TaskType type) {
        description = des;
        this.type = type;
        isDone = false;
    }

    public Task(String des, TaskType type, boolean isDone) {
        description = des;
        this.type = type;
        this.isDone = isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }

    public abstract String parseTask();

    @Override
    public String toString() {
        return description;
    }
}
