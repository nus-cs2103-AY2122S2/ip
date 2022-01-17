import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public String addTask(String task) {
        this.tasks.add(new Task(task));
        return "added " + task;
    }

    public String markTask(int taskId) {
        this.tasks.get(taskId - 1).mark();
        return this.tasks.get(taskId - 1).toString();
    }

    public String unmarkTask(int taskId) {
        this.tasks.get(taskId - 1).unmark();
        return this.tasks.get(taskId - 1).toString();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 1; i <= this.tasks.size(); i++) {
            str += i +  "." + this.tasks.get(i - 1) + "\n";
        }
        return str;
    }
}
