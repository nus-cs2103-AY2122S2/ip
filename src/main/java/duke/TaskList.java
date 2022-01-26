package duke;
import java.util.ArrayList;

/**
 * TaskList class which stores Task objects in an ArrayList.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Construct TaskList object which stores Task objects in an ArrayList.
     *
     * @param loadedTasks   The initial tasks stored in an ArrayList.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds a Todo task to the tasklist.
     *
     * @param task  The task to be added to the list.
     */
    public void addTodo(String task) {
        Task newTask = new Todo(task);
        tasks.add(newTask);
    }

    /**
     * Adds a Deadline task to the tasklist.
     *
     * @param task  The task to be added to the list.
     * @param deadline  The deadline of the task.
     */
    public void addDeadline(String task, String deadline) {
        Task newTask = new Deadline(task, deadline);
        tasks.add(newTask);
    }

    /**
     * Adds an Event task to the tasklist.
     *
     * @param task  The task to be added to the list.
     * @param deadline  The deadline of the task.
     */
    public void addEvent(String task, String deadline) {
        Task newTask = new Event(task, deadline);
        tasks.add(newTask);
    }

    /**
     * Delete the indicated task from the tasklist.
     *
     * @param taskNum   The task number which is to be deleted.
     */
    public Task delete(int taskNum) {
        Task removed = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        return removed;
    }

    /**
     * Marks the indicated task as done.
     *
     * @param taskNum   The task number which is to be marked as done.
     */
    public Task mark(int taskNum) {
        tasks.get(taskNum - 1).setDone();
        return tasks.get(taskNum - 1);
    }

    /**
     * Marks the indicated task as not done.
     *
     * @param taskNum   The task number which is to be marked as not done.
     */
    public Task unmark(int taskNum) {
        tasks.get(taskNum - 1).setNotDone();
        return tasks.get(taskNum - 1);
    }
}
