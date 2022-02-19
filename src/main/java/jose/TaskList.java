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
            Task.Priority priority;

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
                priority = Task.Priority.LOW;
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

    /**
     * Returns the task list.
     *
     * @return Task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the task that corresponds to the given index.
     *
     * @param index Index of a task.
     * @return The specified task.
     * @throws DukeException If the specified task does not exist.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Taskado no existado. Enter a valid task index por favor.");
        }
    }

    /**
     * Returns task list size.
     *
     * @return Task list size.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds task to task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task from task list.
     *
     * @param task Task to be removed.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }
}
