package tasks;

import exceptions.NoSuchTaskException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a taskList.
 *
 * @author Ong Han Yang
 */
public class TaskList {
    /** The container for the items, implemented as an ArrayList*/
    private ArrayList<Task> list;

    /**
     * Constructs a taskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds an item into the taskList.
     *
     * @param item the item to be added.
     */
    public void add(Task item) {
        this.list.add(item);
    }

    /**
     * Adds multiple items at a time to the taskList.
     *
     * @param items all the items to be added.
     */
    public void add(Task... items) {
        this.list.addAll(List.of(items));
    }

    /**
     * Removes an item from the list of tasks.
     *
     * @param taskIndex the index of the item to remove
     * @return the removed task.
     */
    public Task delete(int taskIndex) throws NoSuchTaskException {
        if (taskIndex >= this.list.size() || taskIndex < 0) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        return this.list.remove(taskIndex);
    }

    /**
     * Gets the length of the task list.
     *
     * @return an integer representing the length of the list.
     */
    public int length() {
        return this.list.size();
    }

    /**
     * Marks a task as done or undone.
     *
     * @param taskIndex the index of the task to be marked.
     * @param isDone whether the task is to be done or not.
     * @throws NoSuchTaskException when there is no such task with the index taskNum.
     */
    public void markTask(int taskIndex, boolean isDone) throws NoSuchTaskException {
        if (taskIndex >= this.list.size() || taskIndex < 0) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        this.list.get(taskIndex).markAs(isDone);
    }

    /**
     * Represents a stored task as a String.
     *
     * @param taskIndex the index of the task to be displayed.
     * @return String representation of the task.
     * @throws NoSuchTaskException when there is no such task with the index taskNum.
     */
    public String displayTask(int taskIndex) throws NoSuchTaskException {
        if (taskIndex >= this.list.size() || taskIndex < 0) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        return this.list.get(taskIndex).toString();
    }

    /**
     * Displays the taskList as a String.
     *
     * @return String representation of the tasks.TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                // For the last item, don't append a newline character.
                sb.append(String.format("%d. %s", i + 1, list.get(i).toString()));
            } else {
                sb.append(String.format("%d. %s\n", i + 1, list.get(i).toString()));
            }
        }
        return sb.toString();
    }
}
