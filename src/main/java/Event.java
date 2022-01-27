public class Event extends Task {
    String timeRange;

    public Event(String taskName, String timeRange) {
        super(taskName, "E");
        this.timeRange = timeRange;
    }

    public Event(String taskName, String timeRange, boolean isDone) {
        super(taskName, "E", isDone);
        this.timeRange = timeRange;
    }

    @Override
    public String markAsDone(boolean isDone) {
        if (isDone) {
            this.isDone = true;
            return  " Nice! I've marked this task as done:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (this.isDone ? "X" : " ") + "] " + // [X]
                    this.taskName;
        } else {
            this.isDone = false;
            return "OK, I've marked this task as not done yet:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (this.isDone ? "X" : " ") + "] " + // [X]
                    this.taskName;
        }
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.taskName +
                " (at: " + this.timeRange + ")" +
                "\n";
    }
}
