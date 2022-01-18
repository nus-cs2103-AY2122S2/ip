public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String genDoneSymbol() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = ! isDone;
    }

    public String toString() {
        return description;
    }
}
