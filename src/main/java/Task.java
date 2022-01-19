public class Task {
    private static int totalTasks = 0;
    int index;
    boolean done;
    String taskName;

    public Task(String taskName) {
        this.done = false;
        this.taskName = taskName;
        index = ++totalTasks;
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
        return this.index + "." +
                "[" + (done ? "X" : " ") + "] " +
                this.taskName + "\n";
    }
}
