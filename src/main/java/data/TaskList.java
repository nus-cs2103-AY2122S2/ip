package data;

import task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class holding list of tasks stored by
 * users, and provides method to manipulate
 * the list and save it to disk.
 */
public class TaskList {
    /** Backing store for current task list */
    private final Storage store;

    /** Internal list of tasks */
    private final ArrayList<Task> tasks;

    private TaskList(Storage store, ArrayList<Task> tasks) {
        this.store = store;
        this.tasks = tasks;
    }

    /**
     * Initialise a TaskList instance with store as
     * a backing storage. Also loads the content of the store
     * into its task list.
     *
     * @param store Storage instance this task list was saved on
     * @return TaskList initialised with contents in store, and
     * backed by store
     */
    public static TaskList initTaskList(Storage store) throws IOException {
        ArrayList<Task> tasks = TaskList.generateSavedTasks(store.readAll());
        return new TaskList(store, tasks);
    }

    /**
     * Adds task to task list, and saving to storage.
     *
     * @param task Task to add to task list
     */
    public void addTask(Task task) throws IOException {
        this.tasks.add(task);
        this.saveState();
    }

    /**
     * Deletes task from task list, and saving to storage.
     *
     * @param task Task to add to task list
     */
    public void deleteTask(Task task) throws IOException {
        this.tasks.remove(task);
        this.saveState();
    }

    /**
     * Check task, marking it as done in task list.
     *
     * @param index Index of task to mark as done
     */
    public void checkTask(int index) throws IOException {
        Task task = this.tasks.get(index);
        task.unmarkDone();
        this.saveState();
    }

    /**
     * Uncheck task, marking it as undone in task list.
     *
     * @param index Index of task to mark undone
     */
    public void uncheckTask(int index) throws IOException {
        Task task = this.tasks.get(index);
        task.unmarkDone();
        this.saveState();
    }

    /**
     * Saves current tasks into backing store
     */
    private void saveState() throws IOException {
        List<String> taskData = this.tasks.stream()
                .map(task -> task.encodeTaskData())
                .collect(Collectors.toList());
        this.store.writeAll(new ArrayList<>(taskData));
    }

    private static ArrayList<Task> generateSavedTasks(ArrayList<String> data) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line: data) {
            Task savedTask = Task.decodeTaskData(line);
            tasks.add(savedTask);
        }
        return tasks;
    }
}
