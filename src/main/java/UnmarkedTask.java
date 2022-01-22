public class UnmarkedTask extends Task {
    private final String taskTitle;
    private final String unmarkedTitle;

    public UnmarkedTask(String taskTitle) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        this.unmarkedTitle = taskTitle.replace("[X]", "[ ]");
    }

    public UnmarkedTask(String taskTitle, String taskType) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        String codedType = taskType.equals("todo") ? "[T]" : "";
        this.unmarkedTitle = codedType + "[ ] " + taskTitle;
    }

    public UnmarkedTask(String taskTitle, String deadline, String taskType) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        String codedType = taskType.equals("event") ? "[E]" : "[D]";
        String timing = taskType.equals("event") ? "(at: " + deadline + ")"
                : "(by: " + deadline + ")";
        this.unmarkedTitle = codedType + "[ ] " + taskTitle + " " + timing;
    }

    public Task markTask() {
        if (!this.isMarked()) {
            return new MarkedTask(this.unmarkedTitle);
        }
        else return this;
    }

    public Task unmarkTask() {
        return this;
    }

    public boolean isMarked() {
        return false;
    }

    @Override
    public String toString() {
        return unmarkedTitle;
    }
}
