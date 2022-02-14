package duke.tasks;

public class Task {
    String content;
    boolean markedDone = false;

    public Task(String content) {
        this.content = content;
    }

    public void markAsDone() {
        this.markedDone = true;
    }

    public void markAsNotDone() {
        this.markedDone = false;
    }

    @Override
    public String toString() {
        return "[" + (this.markedDone ? "X" : " ") + "] " + this.content;
    }

}
