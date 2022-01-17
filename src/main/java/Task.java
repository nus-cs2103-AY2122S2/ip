public class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markItem() {
        this.isDone = true;
    }

    public void unmarkItem() {
        this.isDone = false;
    }

    public void incrementTask() {
        totalTasks++;
    }

    public void decrementTask() {
        totalTasks--;
    }

    public String printNoOfTasks() {
        return "Now you have " + totalTasks + " tasks in the list.";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
