public class Task {
    private boolean isDone = false; // not done by default
    private String taskMessage;

    public Task(String message) {
        taskMessage = message;
    }

    @Override
    public String toString() {
        String statusMessage = "[ ]"; // not done by default
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