public class Task {
    private String description;
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static void addToList(Task task) {
        taskList[taskCount++] = task;
    }

    public static Task[] getTaskList() {
        return taskList;
    }

    public static int getTaskCount() {
        return taskCount;
    }
}
