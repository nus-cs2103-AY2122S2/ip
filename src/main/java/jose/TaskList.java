package jose;

import java.util.ArrayList;

import jose.task.Deadline;
import jose.task.Event;
import jose.task.Task;
import jose.task.ToDo;

/**
 * Class that represents a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * The default constructor that sets up an empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }


    /**
     * A constructor that sets up the tasks list based on the given tasks.
     *
     * @param arr An ArrayList containing tasks.
     */
    public TaskList(ArrayList<String> arr) {
        tasks = new ArrayList<>();

        for (String s : arr) {
            String[] task = s.split("\\|");
            boolean isDone = task[2].equals("1");
            Task.Priority priority = null;

            switch (task[1]) {
                case "1":
                    priority = Task.Priority.LOW;
                    break;
                case "2":
                    priority = Task.Priority.MEDIUM;
                    break;
                case "3":
                    priority = Task.Priority.HIGH;
                    break;
                default:
                    System.out.println("Oops");
            }
            
            if (task[0].equals("T")) {
                tasks.add(new ToDo(task[3], isDone, priority));
            } else if (task[0].equals("D")) {
                tasks.add(new Deadline(task[3], isDone, task[4], priority));
            } else {
                tasks.add(new Event(task[3], isDone, task[4], priority));
            }
        }
    }

    /**
     * Finds and returns tasks that contain the given query in its description.
     *
     * @param query An ArrayList containing tasks.
     * @return An ArrayList containing tasks with descriptions that contain the given query.
     */
    public ArrayList<Task> findTasks(String query) {
        ArrayList<Task> results = new ArrayList<>();

        for (Task task : tasks) {
            if (task.matchDescription(query)) {
                results.add(task);
            }
        }

        return results;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }
}
