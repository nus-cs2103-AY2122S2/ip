import java.util.*;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public void add(String name) {
        Task t = new Task(name);
        tasks.add(t);

        System.out.format("added: %s\n", t.getName());
    }

    public void print() {
        // if there are no tasks, inform the user
        if (tasks.size() == 0) {
            System.out.println("No tasks found! (trust me, I've looked everywhere)");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.format("%d. %s\n", i + 1, tasks.get(i));
            }
        }
    }
}
