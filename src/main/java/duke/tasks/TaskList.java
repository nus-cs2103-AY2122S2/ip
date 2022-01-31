package duke.tasks;

import duke.DukeException;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs with an array list of tasks.
     *
     * @param tasks The array list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the string description of a task.
     *
     * @param index The index of task in the list.
     * @return The task string.
     */
    public String getTaskString(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Gets the tasks count string.
     *
     * @return The tasks count string.
     */
    public String printTaskCount() {
        return "Now you have " + tasks.size() + " task(s) in the list.";
    }

    /**
     * Adds a new task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task in the list.
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index!", e);
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task in the list.
     */
    public void markTask(int index) {
        try {
            tasks.get(index).setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index!", e);
        }
    }

    /**
     * Marks a task as not done yet.
     *
     * @param index The index of the task in the list.
     */
    public void unmarkTask(int index) {
        try {
            tasks.get(index).setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index!", e);
        }
    }

    /**
     * Finds the tasks that match search string.
     *
     * @param search The string to match for, case-sensitive.
     * @return An array list of tasks that matches the search string.
     */
    public ArrayList<Task> findTasks(String search) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task t : tasks) {
            if (t.description.contains(search)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Gets the string description of the whole task list.
     *
     * @return The string description.
     */
    public String taskListToString() {
        StringBuilder result = new StringBuilder();
        String title = tasks.isEmpty() ? "You got no task now! Start by adding new tasks."
                : "Here are the task(s) in your list:";
        result.append(title);
        for (int i = 0; i < tasks.size(); ++i) {
            int index = i + 1;
            result.append("\n  " + index + ". " + tasks.get(i).toString());
        }
        return result.toString();
    }

    /**
     * Gets the compact string description of the whole task list.
     *
     * @return The compact string description
     */
    public String taskListFileString() {
        StringBuilder result = new StringBuilder();
        for (Task t : tasks) {
            result.append(t.simpleString() + "\n");
        }
        return result.toString();
    }
}
