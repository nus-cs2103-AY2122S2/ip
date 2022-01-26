public class Event extends Task {
    String timeRange;

    public Event(String taskName, String timeRange) {
        super(taskName, "E");
        this.timeRange = timeRange;
    }

    @Override
    public String markAsDone(boolean isDone) {
        if (isDone) {
            this.done = true;
            return  " Nice! I've marked this task as done:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (done ? "X" : " ") + "] " + // [X]
                    this.taskName;
        } else {
            this.done = false;
            return "OK, I've marked this task as not done yet:" + "\n" + "  " +
                    "     [" + this.taskType + "]" + //[T]
                    "[" + (done ? "X" : " ") + "] " + // [X]
                    this.taskName;
        }
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" +
                "[" + (done ? "X" : " ") + "] " +
                this.taskName +
                "(at: " + this.timeRange + ")" +
                "\n";
    }
}
