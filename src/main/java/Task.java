public abstract class Task {
    private final String taskTitle;
    //mark/unmarked as classes

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public abstract Task markTask();

    public abstract Task unmarkTask();

    public abstract String toString();

    protected abstract boolean isMarked();
}
