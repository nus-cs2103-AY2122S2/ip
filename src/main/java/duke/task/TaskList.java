package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of tasks.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.0
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Empty constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList specifying a list of Tasks.
     *
     * @param tasks list of Tasks to be initialized
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
    }

    /**
     * Returns the length of TaskList.
     *
     * @return number of elements in tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves and returns Task at a given index in TaskList.
     *
     * @param i index of Task to be retrieved in TaskList
     * @return Task at a given index in tasks.
     */
    public Task get(int i) {
        if (i < size() && i >= 0) {
            return tasks.get(i);
        }
        return null;
    }

    /**
     * Deletes Task at a given index in TaskList.
     *
     * @param i index of task to be removed
     */
    public void remove(int i) {
        if (i < size() && i >= 0) {
            tasks.remove(i);
        }
    }

    /**
     * Appends Task to end of TaskList.
     *
     * @param t Task to be added
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Returns matching occurrences of a given word in tasks.
     *
     * @param word keyword to be searched for
     * @return TaskList of the Tasks containing the specified word
     */
    public TaskList find(String word) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task: tasks) {
            if (task.toString().contains(word)) {
                matchingTasks.add(task);
            }
        }

        return new TaskList(matchingTasks);
    }

    /**
     * Default toString method that returns list of all tasks.
     * Note: It appends list of tasks with a string denoting the number
     * of tasks.
     *
     * @return formatted string of all Tasks in TaskList and number of
     * Tasks in TaskList delimited by newlines.
     */
    public String toString() {
        if (tasks.isEmpty()) {
            return  "You now have 0 tasks on your list.";
        }

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;

            str.append("  ")
                    .append(index)
                    .append(". ")
                    .append(tasks.get(i))
                    .append("\n");
        }

        str.append("You have ")
                .append(tasks.size())
                .append(" tasks on your list.");

        return str.toString();
    }
}