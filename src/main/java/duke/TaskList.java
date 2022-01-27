package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class TaskList {
    protected List<Task> tasks;

    /**
     * Default constructor if program is run for the first time.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor that retrieves the TaskList elements from previous running of program.
     * @param list the list of tasks to be inputted
     */
    public TaskList(List<Task> list) {
        this.tasks = list;
    }

    /**
     * Returns the task at the provided index from the list.
     * @param index the index of the task
     * @return The task at the given index
     * @throws DukeException throws if the index does not exist in the list, or if the index was not an integer
     */
    public Task getTaskAtIndex(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Pardon me, but the provided index does not exist in your list.");
        }
    }

    /**
     * Adds the provided task to the list of tasks.
     * @param t The task to be added
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes the task at the provided index from the list
     * @param index the index of the task
     * @return the task that was removed
     * @throws DukeException throws if the index does not exist in the list, or if the index is not an integer
     */
    public Task removeTask(int index) throws DukeException {
        this.getTaskAtIndex(index); //make sure task exists

        return this.tasks.remove(index);
    }

    /**
     * Finds the tasks that match a given keyword.
     * Note: implementation is subject to change as it is not very efficient.
     * @param keyword the string to compare with
     * @return list of tasks that match the keyword in string form
     */
    public String find(String keyword) {
        StringBuilder foundStrings = new StringBuilder("");
        for (int i = 0; i < tasks.size(); i++) {
            String currDescription = tasks.get(i).getDescription().toLowerCase();

            if (currDescription.contains(keyword)) {
                foundStrings.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return foundStrings.toString();
    }

    /**
     * Returns the number of tasks currently in the list.
     */
    public int getTasksCount() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks
     * @return the list of tasks
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the toString implementation of the list of tasks ordered numerically.
     * @return the string implementation of the list of tasks
     */
    public String toString() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }

        return listOfTasks.toString();
    }
}
