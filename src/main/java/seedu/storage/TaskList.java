package seedu.storage;

import java.util.ArrayList;
import java.util.Collections;

import seedu.duke.DukeException;
import seedu.task.Task;

/**
 * The task list class
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor to initialise an already filled list
     *
     * @param taskList The list to be initialised from
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = new ArrayList<>(taskList);
    }

    /**
     * Add task to list
     *
     * @param task The task to be added to the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Sort list based on descending priority level
     */
    public void sort() {
        Collections.sort(tasks);
    }

    /**
     * Gets the entire list
     *
     * @return The task list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Removes task from list based on index
     *
     * @param idx Index of the task to be deleted
     * @return The deleted task
     * @throws DukeException Index not found in list
     */
    public Task remove(int idx) throws DukeException {
        Task t = get(idx);
        tasks.remove(idx);
        return t;
    }

    /**
     * Gets the task based on its index
     *
     * @param idx Index of the task
     * @return The task found by the index
     * @throws DukeException Index not found in list
     */
    public Task get(int idx) throws DukeException {
        try {
            return tasks.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task does not exist.");
        }
    }

    /**
     * Finds all tasks with the given search string
     *
     * @param search The search term
     * @return A list of tasks in string form that contains the search term.
     */
    public String find(String search) {
        StringBuilder s = new StringBuilder("Found:\n");

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(search)) {
                s.append("  ");
                s.append(i + 1);
                s.append(". ");
                s.append(tasks.get(i).toString());
                s.append("\n");
            }
        }

        return s.toString().trim();
    }

    /**
     * Returns the tasks of the list
     *
     * @return A string representation of the tasks of the list
     */
    @Override
    public String toString() {

        if (tasks.isEmpty()) {
            return "Empty list";
        }

        StringBuilder out = new StringBuilder("Your List:\n");

        for (int i = 0; i < tasks.size(); i++) {
            out.append("  ");
            out.append(i + 1);
            out.append(". ");
            out.append(tasks.get(i).toString());
            out.append("\n");
        }

        return out.toString().trim();
    }
}
