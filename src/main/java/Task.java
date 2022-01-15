public abstract class Task {

    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
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
}
