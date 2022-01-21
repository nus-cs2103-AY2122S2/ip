public class UnmarkedTask extends Task {
    private final String taskTitle;
    private final String unmarkedTitle;

    public UnmarkedTask(String taskTitle) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        this.unmarkedTitle = "[ ] " + taskTitle;
    }

    @Override
    public String toString() {
        return unmarkedTitle;
    }
}
