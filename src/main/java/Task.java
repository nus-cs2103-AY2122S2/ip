public class Task {
    private boolean isDone;
    private String task;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String toString() {
        String statusString = isDone ? "[X] " : "[ ] ";
        return statusString + task;   
    }
}
