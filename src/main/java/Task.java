public abstract class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String switchMark(String instr) {
        if ((instr.equals("mark") && !isDone)|| (instr.equals("unmark") && isDone)) {
            this.isDone = !this.isDone;
        }
        if (isDone) {
            return "Nice! I've marked this task as done:\n  " + toString();
        }
        return "OK, I've marked this task as not done yet:\n  " + toString();
    }

    public String getStatusIcon() {
        return isDone? "X": " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), taskName);
    }

    public String encode() {
        return String.format("%d <> %s", isDone? 1: 0, taskName);
    }
}
