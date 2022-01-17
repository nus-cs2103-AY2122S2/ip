public class Task {
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

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return description;
    }
}
