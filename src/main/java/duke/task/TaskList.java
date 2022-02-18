package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

/**
 * Represents a collection of tasks.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 0.1
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Empty constructor for TaskList.
     * Initializes the TaskList with an empty linked list.
     */
    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    /**
     * Constructor for TaskList.
     * Initializes TaskList with a given list of Tasks.
     *
     * @param tasks list of Task objects
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new LinkedList<>();
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
     * @param t  Task to be added
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Searches for occurrences of a given word in tasks.
     * Returns a list of Tasks that contain it.
     *
     * @param word keyword to be searched for
     * @return TaskList of the Tasks containing the specified word
     */
    public TaskList find(String word) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(word)) {
                matchingTasks.add(tasks.get(i));
            }
        }
        return new TaskList(matchingTasks);
    }

    /**
     * Default toString method that returns a string of all tasks in
     * TaskList and the number of tasks in TaskList.
     *
     * @return formatted string of all Tasks in TaskList and number of
     * Tasks in TaskList delimited by newlines.
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).isEmpty()) {
                int index = i + 1;
                s += "  " + index + ". " + tasks.get(i) + "\n";
            } else {
                s += "You have " + i + " tasks on your list.";
                break;
            }
        }
        if (tasks.isEmpty()) {
            s =  "You now have 0 tasks on your list.";
        }

        return s;
    }

    public int length() {
        return tasks.size();
    }
}