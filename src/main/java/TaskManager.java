import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public String addToDo(String description) {
        this.tasks.add(new Todo(description));
        return this.tasks.get(this.tasks.size() - 1).toString();
    }

    public String addDeadline(String description, String by) {
        this.tasks.add(new Deadline(description, by));
        return this.tasks.get(this.tasks.size() - 1).toString();
    }

    public String addEvent(String description, String at) {
        this.tasks.add(new Event(description, at));
        return this.tasks.get(this.tasks.size() - 1).toString();
    }

    public String markTask(int taskId) {
        this.tasks.get(taskId - 1).mark();
        return this.tasks.get(taskId - 1).toString();
    }

    public int size() {
        return this.tasks.size();
    }

    public String unmarkTask(int taskId) {
        this.tasks.get(taskId - 1).unmark();
        return this.tasks.get(taskId - 1).toString();
    }

    public String delete(int taskId) {
        Task toDelete = this.tasks.get(taskId - 1);
        this.tasks.remove(taskId - 1);
        return toDelete.toString();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 1; i <= this.tasks.size(); i++) {
            str += i + "." + this.tasks.get(i - 1) + "\n";
        }
        return str;
    }
}
