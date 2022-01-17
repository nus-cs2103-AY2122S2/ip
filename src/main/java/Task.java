public class Task {
    public String taskName;
    public boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void markDone(Task task) {
        task.done = true;
    }

    public void unmarkDone(Task task) {
        this.done = false;
    }
}
