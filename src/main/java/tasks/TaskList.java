package tasks;

import Exceptions.NoSuchTaskException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a tasks.TaskList, that contains up to 100 tasks.
 *
 * @author Ong Han Yang
 */
public class TaskList {
    /** The container for the items, implemented as an ArrayList*/
    private ArrayList<Task> list;

    /**
     * Constructor for a tasks.TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Method to add an item into the tasks.Task List
     *
     * @param item the item to be added.
     */
    public void add(Task item) {
        this.list.add(item);
    }

    /**
     * Overloaded method to add multiple items at a time.
     * @param items all the items to be added.
     */
    public void add(Task... items) {
        this.list.addAll(List.of(items));
    }

    /**
     * Method to remove an item from the list of tasks.
     *
     * @param taskIndex the index of the item to remove
     * @return the removed task.
     */
    public Task delete(int taskIndex) throws NoSuchTaskException {
        if (taskIndex >= this.list.size()) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        return this.list.remove(taskIndex);
    }

    /**
     * Method to return the length of the task list.
     * @return an integer representing the length of the list.
     */
    public int length() {
        return this.list.size();
    }

    /**
     * Method to mark a task as done or undone.
     *
     * @param taskIndex the index of the task to be marked.
     * @param isDone whether the task is to be done or not.
     * @throws NoSuchTaskException when there is no such task with the index taskNum.
     */
    public void markTask(int taskIndex, boolean isDone) throws NoSuchTaskException {
        if (taskIndex >= this.list.size()) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        this.list.get(taskIndex).markAs(isDone);
    }

    /**
     * Method to display a stored task as a String.
     *
     * @param taskIndex the index of the task to be displayed.
     * @return String representation of the task.
     * @throws NoSuchTaskException when there is no such task with the index taskNum.
     */
    public String displayTask(int taskIndex) throws NoSuchTaskException {
        if (taskIndex >= this.list.size()) {
            throw new NoSuchTaskException("There is no task with index " + taskIndex);
        }
        return this.list.get(taskIndex).toString();
    }

    /**
     * Overridden toString method to display the tasks.TaskList as a String.
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
