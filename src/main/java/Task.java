public class Task {
    protected boolean isDone = false;
    protected String taskMessage;

    public Task(String message) throws EmptyMessageException {
        if (message.equals("")) {
            throw new EmptyMessageException();
        }

        taskMessage = message;
    }

    protected Task(Task task) {
        this(task.getTask());
    }

    protected Task() {
        taskMessage = "";
    }

    @Override
    public String toString() {
        String statusMessage = "[ ]";
        if (isDone) {
            statusMessage = "[X]";
        }

        return String.format("%s %s", statusMessage, taskMessage);
    }

    public boolean status() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public String getTask() {
        return taskMessage;
    }
}