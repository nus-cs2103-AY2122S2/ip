package core.tasks;

import core.exceptions.TaskAlreadyDoneException;
import core.exceptions.TaskAlreadyUnmarkedException;
import utilities.OutputFormatter;

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

    public Task(String taskDescription) {
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
            return "X"; // "\u2713";
        } else {
            return " "; // "\u2718";
        }
    }

    public void complete() throws TaskAlreadyDoneException {
        if (this.isCompleted()) {
            throw new TaskAlreadyDoneException();
        }
        this.isCompleted = true;
    }

    public void markAsNotComplete() throws TaskAlreadyUnmarkedException {
        if (!this.isCompleted()) {
            throw new TaskAlreadyUnmarkedException();
        }
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("[", status(), "] ", this.taskDescription);
        return outputFormatter.getFormattedOutput();
    }
}
