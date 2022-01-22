import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTask(int number) {
        tasks.get(number).markTaskDone();
    }

    public void unmarkTask(int number) {
        tasks.get(number).unmarkTaskDone();
    }

    public void deleteTask(int number) {
        tasks.remove(number);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}

