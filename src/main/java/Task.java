public class Task {
    private final String name;
    private boolean isDone;
    //can make isDone final for good practice

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void switchStatus() {
        this.isDone = !this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }
}
