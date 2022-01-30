import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int n) {
        tasks.remove(n);
    }

    public void markTask(int n) {
        tasks.get(n).mark();
    }

    public void unmarkTask(int n) {
        tasks.get(n).unmark();
    }

    public String toString() {
        String string = "";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String status = task.getStatus() ? "X" : " ";
            string += String.format("%d. %s", i, task.toString());
        }
        return string;
    }

}