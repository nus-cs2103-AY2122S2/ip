package duke.tasks;

public class Task {
    private String content;
    private boolean markedDone = false;

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
        String markedDoneIndicator = this.markedDone ? "X" : " ";
        return String.format("[%s] %s", markedDoneIndicator, this.content);
    }

    public String toSaveData() {
        String markedDoneIndicator = this.markedDone ? "1" : "0";
        return String.format("%s|%s", markedDoneIndicator, this.content);
    }

}
