package duke.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.task.Task;

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
     * Returns ArrayList of string description of each
     * task in taskList.
     *
     * @return ArrayList of tasks description.
     */
    public ArrayList<String> getTaskDescriptions() {
        return this.tasks.stream()
                .map(Task::getDescription)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns the length of tasks array.
     *
     * @return Size of tasks ArrayList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the filtered ArrayList of Task by applying
     * filter function on each Task in list.
     *
     * @param filter Filter function to applied on each task returning
     *               a boolean on whether the task passes the filter.
     * @return Filtered list of tasks.
     */
    public ArrayList<Task> filterTasks(Function<Task, Boolean> filter) {
        Predicate<Task> taskFilter = new Predicate<Task>() {
            @Override
            public boolean test(Task task) {
                return filter.apply(task);
            }
        };

        return this.tasks
                .stream()
                .filter(taskFilter)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Initialise a TaskList instance with store as
     * a backing storage. Also loads the content of the store
     * into its task list.
     *
     * @param store Storage instance this task list was saved on.
     * @return TaskList initialised with contents in store, and
     * backed by store.
     */
    public static TaskList initTaskList(Storage store) throws IOException {
        ArrayList<Task> tasks = TaskList.generateSavedTasks(store.readAll());
        return new TaskList(store, tasks);
    }

    /**
     * Adds task to task list, and saving to storage.
     *
     * @param task Task to add to task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.saveState();
    }

    /**
     * Deletes task from task list, and saving to storage.
     *
     * @param index Index of task to delete.
     * @return The deleted Task.
     * @throws IllegalArgumentException If index is out of list range.
     */
    public Task deleteTask(int index) throws IllegalArgumentException {
        if (!isValidIndex(index)) {
            throw new IllegalArgumentException("Invalid index for current list");
        }
        Task task = this.tasks.remove(index);
        this.saveState();
        return task;
    }

    /**
     * Check task, marking it as done in task list.
     *
     * @param index Index of task to mark as done.
     * @return The checked Task.
     * @throws IllegalArgumentException If index is out of list range.
     */
    public Task checkTask(int index) {
        if (!isValidIndex(index)) {
            throw new IllegalArgumentException("Invalid index for current list");
        }
        Task task = this.tasks.get(index);
        task.markDone();
        this.saveState();
        return task;
    }

    /**
     * Uncheck task, marking it as undone in task list.
     *
     * @param index Index of task to mark undone.
     * @return The unchecked Task.
     * @throws IllegalArgumentException If index is out of list range.
     */
    public Task uncheckTask(int index) {
        if (!isValidIndex(index)) {
            throw new IllegalArgumentException("Invalid index for current list");
        }
        Task task = this.tasks.get(index);
        task.unmarkDone();
        this.saveState();
        return task;
    }

    /**
     * Saves current state of TaskList into backing store.
     */
    private void saveState() {
        List<String> taskData = this.tasks.stream()
                .map(task -> task.encodeTaskData())
                .collect(Collectors.toList());
        try {
            this.store.writeAll(new ArrayList<>(taskData));
        } catch (IOException e) {
            System.err.println("Backed file cannot be written to: " + store);
        }
    }

    /**
     * Checks if given index is a valid index to operate
     * on the list (0 <= index < tasks.size()).
     *
     * @param index Index to check.
     * @return Boolean of whether index is valid.
     */
    private boolean isValidIndex(int index) {
        return (0 <= index) && (index < this.tasks.size());
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
