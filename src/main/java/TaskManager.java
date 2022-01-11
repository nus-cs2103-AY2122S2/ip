import java.util.LinkedHashMap;
import java.util.Map;

public class TaskManager {
    private final Map<String, Task> tasks = new LinkedHashMap<>();

    public void addTask(Task task) {
        if (tasks.containsKey(task.description)) {
            System.out.println("Task already exists!");
        } else {
            tasks.put(task.description, task);
            System.out.println("added: " + task.description);
        }
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i ++) {
            String description = (String) tasks.keySet().toArray()[i];
            System.out.println(i + 1 + "." + " [" + tasks.get(description).getStatusIcon() +"] " + description);
        }
    }

    public void mark(int index) {
        try {
            String description = (String) tasks.keySet().toArray()[index];
            Task task = tasks.get(description);
            if (task.isDone) {
                System.out.println("Task is already marked!");
            } else {
                task.isDone = true;
                System.out.println("Nice! I've marked this task as done: \n" + " [" + task.getStatusIcon() + "] " + task.description);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No such task, check your index!");
        }
    }

    public void unmark(int index) {
        try {
            String description = (String) tasks.keySet().toArray()[index];
            Task task = tasks.get(description);
            if (task.isDone) {
                task.isDone = false;
                System.out.println("OK, I've marked this task as not done yet: \n" + " [" + task.getStatusIcon() + "] " + task.description);
            } else {
                System.out.println("Task is already unmarked!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No such task, check your index!");
        }
    }
}
