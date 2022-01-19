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
            return "[" + (done ? "X" : " ") + "] " +
                    this.taskName + "\n";
        } else {
            this.done = false;
            return "[" + (done ? "X" : " ") + "] " +
                    this.taskName + "\n";
        }
    }

    @Override
    public String toString() {
        return this.index + "." +
                "[" + (done ? "X" : " ") + "] " +
                this.taskName + "\n";
    }
}
