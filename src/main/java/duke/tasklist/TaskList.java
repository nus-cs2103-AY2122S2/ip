package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a list of tasks.
 *
 * Contains methods to add, remove, mark and unmark Tasks in a list.
 */
public class TaskList {
    private List<Task> taskList = new ArrayList<>();

    /**
     * Default constructor of a TaskList.
     *
     * Will initialise TaskList with an empty ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor of a TaskList.
     *
     * @param list List of Tasks that TaskList should initialise with.
     */
    public TaskList(List<Task> list) {
        taskList = list;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added.
     * @return True if task has been successfully added.
     */
    public boolean add(Task task) {
        return taskList.add(task);
    }

    /**
     * Removes a tasks from the TaskList from a given index.
     *
     * @param index Index position of the task to be removed.
     * @return The removed task if given a valid index, otherwise returns null.
     */
    public Task remove(int index) {
        if (index >= 0 && index < taskList.size()) {
            return taskList.remove(index);
        }
        return null;
    }

    /**
     * Returns a copy of the list of tasks managed by TaskList.
     *
     * @return List of tasks.
     */
    public List<Task> list() {
        List<Task> copy = new ArrayList<>();
        for (Task task : taskList) {
            copy.add(task);
        }
        return copy;
    }


    /**
     * Returns the integer size of the current list.
     *
     * @return Number of tasks in the current list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Marks a task at a given index as done.
     *
     * @param index Index position of the task to be marked in the list.
     * @return Task that was marked.
     */
    public Task mark(int index) {
        Task task = taskList.get(index);
        task.mark();
        return task;
    }

    /**
     * Marks a task at a given index as not done.
     *
     * @param index Index position of the task to be unmarked in the list.
     * @return Task that was unmarked.
     */
    public Task unmark(int index) {
        Task task = taskList.get(index);
        task.unmark();
        return task;
    }
}
