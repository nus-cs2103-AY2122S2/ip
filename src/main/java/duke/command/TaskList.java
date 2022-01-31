package duke.command;

import java.util.ArrayList;

import duke.exception.InvalidActionException;
import duke.task.Task;

/** A list to manage the tasks in Duke */
public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Creates a new TaskList instance.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        list.add(t);
    }

    /**
     * Returns the task last added to the list.
     *
     * @return The task last added to the list.
     */
    public Task getLast() {
        return list.get(list.size() - 1);
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Marks the specified task as complete.
     *
     * @param index The index of the task to be marked as complete.
     * @return The String representation of the task marked as complete.
     * @throws InvalidActionException If the task is already complete.
     */
    public String markItemDone(int index) throws InvalidActionException {
        Task t = list.get(index);
        t.markAsDone();
        return "Task done! \\(n_n)/\n " + t.toString();
    }

    /**
     * Marks the specified task as incomplete.
     *
     * @param index The index of the task to be marked as incomplete.
     * @return The String representation of the task marked as incomplete.
     * @throws InvalidActionException If the task is already incomplete.
     */
    public String markItemUndone(int index) throws InvalidActionException {
        Task t = list.get(index);
        t.markUndone();
        return "Task not done =(\n " + t.toString();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted from the task list.
     * @return The String representation of the task deleted from the task list.
     */
    public String deleteItem(int index) {
        Task t = list.remove(index);
        return "deleted this item O_O:\n  " + t + "\nNow there are " + list.size() + " tasks on the list x)";
    }

    /**
     * Gets the list of tasks in the list.
     *
     * @return The ArrayList of tasks in the list.
     */
    public ArrayList<Task> getItems() {
        return list;
    }

    /**
     * Gets a sub-list of tasks that contain a specific keyword/phrase.
     *
     * @param description The specific keyword/phrase to search for
     * @return A sub-list of tasks that contain a specific keyword/phrase.
     */
    public ArrayList<Task> findItems(String description) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task item : list) {
            String s = item.toString();
            if (s.contains(description)) {
                newList.add(item);
            }
        }
        return newList;
    }
}
