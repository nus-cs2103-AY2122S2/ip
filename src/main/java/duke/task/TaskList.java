package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class which handles List manipulation for Tasks
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Default constructor for TaskList
     * Initialises an empty List
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor for TaskList
     * Passed in task overwrites the list
     *
     * @param tasks list to overwrite in TaskList
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list
     *
     * @return the task list
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks the task at index as done
     *
     * @param index index of task to mark
     */
    public void markTaskDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at index as not done
     *
     * @param index index of task to unmark
     */
    public void markTaskNotDone(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Returns the task at index
     *
     * @param index index of task
     * @return task at index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at index in the list
     *
     * @param index index of task
     * @return removed task
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Add the specified task into the back of the list
     *
     * @param task Task to add into list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> findTasks(String word) {
        List<Task> tempTasks = new ArrayList<>();
        for (int i = 0 ;i < tasks.size();i++) {
            if (tasks.get(i).getDescription().contains(word)) {
                tempTasks.add(tasks.get(i));
            }
        }
        return tempTasks;
    }

}
