import java.util.LinkedHashMap;
import java.util.Map;

public class TaskManager {
    private final Map<String, Task> tasks = new LinkedHashMap<>();

    public void addTask(Task task) {
        if (tasks.containsKey(task.description)) {
            System.out.println("Task already exists!");
        } else {
            tasks.put(task.description, task);
            if (tasks.size() <= 1) {
                System.out.println("Yes master, I have added: \n" + taskFormatter(task) + "\nNow you have " + tasks.size() + " task in the list.");
            } else {
                System.out.println("Yes master, I have added: \n" + taskFormatter(task) + "\nNow you have " + tasks.size() + " tasks in the list.");
            }
        }
    }

    public void list() {
        if (tasks.size() == 0) {
            System.out.println("Your todo list is empty, go get something done!");
        } else {
            for (int i = 0; i < tasks.size(); i ++) {
                String description = (String) tasks.keySet().toArray()[i];
                Task task = tasks.get(description);
                System.out.println(i + 1 + "." + this.taskFormatter(task));
            }
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
                System.out.println("Nice! I've marked this task as done: \n" + this.taskFormatter(task));
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
                System.out.println("OK, I've marked this task as not done yet: \n" + this.taskFormatter(task));
            } else {
                System.out.println("Task is already unmarked!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No such task, check your index!");
        }
    }

    public String taskFormatter(Task task) {
        String time = "";
        if (task.isDeadline) {
            time = "(By: " + task.time + ")";
        } else if (task.isEvent) {
            time = "(At: " + task.time + ")";
        }
        return " [" + task.getTaskTypeIcon() +"][" + task.getStatusIcon() + "] " + task.description + time;
    }
}
