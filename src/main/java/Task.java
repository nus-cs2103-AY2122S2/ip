public abstract class Task {

    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void markTask() {
        isDone = true;
    }

    public void unmarkTask() {
        isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", isDone? "X" : " ", name);
    }

    public String toData() {
        return String.format("%d|%s", isDone? 1 : 0, name);
    }
}
