public class MarkedTask extends Task {
    private final String taskTitle;
    private final String markedTitle;

    public MarkedTask(String taskTitle) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        this.markedTitle = "[X] " + taskTitle;
    }

    @Override
    public String toString() {
        return markedTitle;
    }
}
