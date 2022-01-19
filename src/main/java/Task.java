public class Task {
    private String description;
    private boolean isDone;
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "[✓]" : "[ ]";
    }

    public static void markAsDone(int index) {
        taskList[index].isDone = true;
        System.out.println(taskList[index].getStatusIcon() + " " + taskList[index].description);
    }

    public static void markAsNotDone(int index) {
        taskList[index].isDone = false;
        System.out.println(taskList[index].getStatusIcon() + " " + taskList[index].description);
    }

    public static void addToList(Task task) {
        taskList[taskCount++] = task;
        System.out.println(" added: " + task.description);
    }

    public static void printTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + taskList[i].getStatusIcon() + " " + taskList[i].description);
        }
    }
}
