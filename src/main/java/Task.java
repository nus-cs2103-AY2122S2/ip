public abstract class Task {
    boolean done;
    String taskName;
    String taskType;

    public Task(String taskName, String taskType) {
        this.done = false;
        this.taskName = taskName;
        this.taskType = taskType;
    }

    public String markAsDone(boolean isDone) {
        if (isDone) {
            this.done = true;
            return  " Nice! I've marked this task as done:" + "\n" + "  " +
                    "     [" + (done ? "X" : " ") + "] " +
                    this.taskName;
        } else {
            this.done = false;
            return "OK, I've marked this task as not done yet:" + "\n" + "  " +
                    "     [" + (done ? "X" : " ") + "] " +
                    this.taskName;
        }
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " +
                this.taskName + "\n";
    }
}
