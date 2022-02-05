package luca.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Class containing the list of Tasks loaded to the chat bot.
 */
public class TaskList {

    /** List of task. */
    private ArrayList<Task> taskList;

    /**
     * Constructor to create empty task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor to create TaskList object with loaded task.
     *
     * @param taskList list of tasks as an array list.
     */
    private TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates a new TaskList from a list of Tasks.
     *
     * @param tasks list of tasks.
     */
    public static TaskList of(ArrayList<Task> tasks) {
        TaskList taskList = new TaskList(tasks);
        return taskList;
    }

    /**
     * Creates a new Task list from the given tasks.
     *
     * @param tasks tasks to be added to the list.
     */
    public static TaskList of(Task... tasks) {
        ArrayList<Task> arrayList = new ArrayList<>(Arrays.asList(tasks));
        TaskList taskList = new TaskList(arrayList);
        return taskList;
    }

    /**
     * Creates an empty TaskList.
     */
    public static TaskList emptyList() {
        TaskList taskList = new TaskList(new ArrayList<>());
        return taskList;
    }

    /**
     * Retrieves the task at particular index.
     *
     * @param index index to retrieve task from.
     * @return task at index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return size of list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task task to be added to the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at particular index.
     *
     * @param index the index to remove task from.
     * @return task removed.
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * Outputs a stream of tasks.
     */
    public Stream<Task> stream() {
        return taskList.stream();
    }

    /**
     * Outputs string describing the number of task in
     * the task list.
     *
     * @return string with number of tasks.
     */
    public String sizeDescription() {
        return "Now you have " + size() + (size() == 1 ? " task" : " tasks")
                + " in the list.";
    }
}
