package duke.tasks;

public class Task {

    private String content;
    private boolean isDone = false;

    public Task(String content) {
        this.content = content;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String markedDoneIndicator = this.isDone ? "X" : " ";
        return String.format("[%s] %s", markedDoneIndicator, this.content);
    }

    public String toSaveData() {
        String markedDoneIndicator = this.isDone ? "1" : "0";
        return String.format("%s|%s", markedDoneIndicator, this.content);
    }

}
