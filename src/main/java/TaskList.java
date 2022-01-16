import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, tasks.get(i)));
        }
    }

    public void markTask(int number) {
        tasks.get(number - 1).isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(number - 1));
    }

    public void unmarkTask(int number) {
        tasks.get(number - 1).isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(number - 1));
    }
}
