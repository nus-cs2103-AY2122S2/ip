package core;

public class Task {
    protected String taskDescription;
    protected boolean isCompleted;
    private final int taskId = createID();

    private static int idCounter = 1;

    static int createID() {
        return idCounter++;
    }

    private Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isCompleted = isDone;
    }

    private Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
    }

    public static Task getInstance(String taskDescription) {
        return new Task(taskDescription);
    }

    public static Task getInstance(String taskDescription, boolean isDone) {
        return new Task(taskDescription, isDone);
    }

    public int getTaskId() {
        return this.taskId;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public String status() {
        if (this.isCompleted) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public void complete() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }
}
