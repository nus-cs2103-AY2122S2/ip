package tasks;

import enums.TaskType;
import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected TaskType taskType;
    protected boolean isDone;

    protected static final ArrayList<Task> TASKS = new ArrayList<>();

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.taskType = taskType;
        this.isDone = false;
    }

    public static ArrayList<Task> getTasks() {
        return Task.TASKS;
    }

    public static void addTask(Task newTask) {
        Task.TASKS.add(newTask);
    }

    public static void deleteTask(int taskIndex) { Task.TASKS.remove(taskIndex); }

    public static Task getTask(int taskIndex) {
        return Task.TASKS.get(taskIndex);
    }

    public static int getTaskCount() {
        return Task.TASKS.size();
    }

    public static String taskCountToString() {
        return String.format("Now you have %d task(s) in the list.", Task.getTaskCount());
    }

    public String getDescription() {
        return this.description;
    }

    public TaskType getTaskType() { return this.taskType; }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskType(), this.getStatusIcon(), this.getDescription());
    }
}