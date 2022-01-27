package paggro.task;

public abstract class Task {
    String description;
    public boolean isDone;

    public Task(String des) {
        description = des;
        isDone = false;
    }

    public Task(String des, boolean isDone) {
        description = des;
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
