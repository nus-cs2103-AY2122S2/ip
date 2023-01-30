package ultoi.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ultoi.task.Task;

/**
 * Represents a list of tasks.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates a list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates a list of tasks from a given list of tasks.
     *
     * @param tasks Given list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a task from the list.
     *
     * @param index Index of the task.
     * @return Task at the index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {

        assert task != null : "Added task is null";

        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of the task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index Index of the task to be marked as done.
     */
    public void mark(int index) {
        getTask(index).markAsDone();
    }

    /**
     * Marks a task in the list as undone.
     *
     * @param index Index of  the task to be marked as undone.
     */
    public void unmark(int index) {
        getTask(index).markAsUndone();
    }

    /**
     * Generates a message describing the number of tasks in the list.
     *
     * @return String describing the number of tasks in the list.
     */
    public String generateNumOfTasksMsg() {
        return "Now you have " + size() + " task(s) in total.";
    }

    /**
     * Finds tasks with the given keyword.
     *
     * @param keyword Keyword to be found.
     * @return A task list that contains tasks taht contains the keyword.
     */
    public TaskList findTasksWith(String keyword) {
        List<Task> matchingTasks = new ArrayList<Task>(this.tasks.stream().filter(
            task -> task.getDescription().contains(keyword)).collect(Collectors.toList()));

        return new TaskList(matchingTasks);
    }

    /**
     * Sorts the tasks by time.
     */
    public void sort() {
        Comparator<Task> taskComparator = new Comparator<Task>() {
            public int compare(Task taskA, Task taskB) {
                if (taskA.getDateTime() == null && taskB.getDateTime() == null) {
                    return taskA.getDescription().compareTo(taskB.getDescription());
                } else if (taskA.getDateTime() == null) {
                    return 1;
                } else if (taskB.getDateTime() == null) {
                    return -1;
                } else {
                    return taskA.getDateTime().compareTo(taskB.getDateTime());
                }
            }
        };

        Collections.sort(this.tasks, taskComparator);
    }

    /**
     * Returns the standard input strings of all the tasks in the list.
     *
     * @return Input strings of the tasks in the list.
     */
    public String toInputString() {
        String str = "";

        for (int i = 0; i < size(); i++) {
            str += getTask(i).toInputString();
            if (getTask(i).isDone()) {
                str = str + "\nmark " + (i + 1);
            }
            if (i < size() - 1) {
                str = str + "\n";
            }
        }
        return str;
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < size(); i++) {
            str = str + (i + 1) + ". " + getTask(i).toString();
            if (i < size() - 1) {
                str = str + "\n";
            }
        }

        return str;
    }
}
