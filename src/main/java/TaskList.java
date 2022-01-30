import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public void printTaskCount() {
        System.out.println(String.format("Now you have %d task(s) in your list.", this.getSize()));
    }

    public void printTaskAdded() {
        System.out.println("Got it. I've added this task:");
        System.out.println(this.get(this.getSize() - 1).toString());
        printTaskCount();
    }

    public String getTaskStatement(int i) {
        return this.get(i).toString();
    }

}
