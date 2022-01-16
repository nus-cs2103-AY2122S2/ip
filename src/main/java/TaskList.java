import java.util.*;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public void add(String name) {
        int taskId = tasks.size() + 1; // taskId numbering starts from 1
        Task t = new Task(taskId, name);
        tasks.add(t);

        System.out.format("added: %s\n", t.getName());
    }

    public void markTask(int taskId) {
        for (Task t : tasks) {
            if (t.taskId == taskId) {
                t.mark();

                break;
            }
        }
    }

    public void unMarkTask(int taskId) {
        for (Task t : tasks) {
            if (t.taskId == taskId) {
                t.unMark();

                break;
            }
        }
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
