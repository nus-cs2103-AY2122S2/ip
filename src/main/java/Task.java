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
        return this.isDone ? "[X]" : "[ ]";
    }

    public static void markAsDone(int index) {
        taskList[index].isDone = true;
        System.out.println("  " + taskList[index].toString());
    }

    public static void markAsNotDone(int index) {
        taskList[index].isDone = false;
        System.out.println("  " + taskList[index].toString());
    }

    public static void addToList(Task task) {
        if (taskCount == 0) {
            System.out.println(" Added! Now you have 1 item in your tasks list.");
        } else {
            System.out.println(" Added! Now you have " + (taskCount + 1) + " items in your tasks list.");
        }
        taskList[taskCount] = task;
        System.out.println("  " + taskList[taskCount++].toString());
    }

    public static void printAllTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println("   " + (i + 1) + "." + taskList[i].toString());
        }
    }

    @Override
    public String toString() {
        return (this.getStatusIcon() + " " + this.description);
    }
}
