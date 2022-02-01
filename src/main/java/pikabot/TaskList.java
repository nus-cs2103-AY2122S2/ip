package pikabot;

import java.util.ArrayList;

import pikabot.task.Task;

/**
 * Contains tasks and performs operations to add, delete, mark, and find tasks.
 */
public class TaskList {

    protected ArrayList<Task> taskList;

    /**
     * Constructs a TaskList.
     *
     * @param taskList ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Calculates the current number of tasks in the TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    public int noOfTasks() {
        return this.taskList.size();
    }

    /**
     * Retrieves a task at a particular index in the TaskList.
     *
     * @param index Index of task to be retrieved, zero-based indexing.
     * @return Task retrieved.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added to the TaskList.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param taskNumber Task to be removed from the TaskList.
     */
    public void delete(int taskNumber) {
        int taskIndex = taskNumber - 1;
        taskList.remove(taskIndex);
    }

    /**
     * Marks a task from the TaskList as done.
     *
     * @param taskNumber Index of task to be marked as done, one-based indexing.
     */
    public void markTaskAsDone(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        currTask.markAsDone();
    }

    /**
     * Unmarks a task from the TaskList.
     *
     * @param taskNumber Index of task to be unmarked, one-based indexing.
     */
    public void markTaskAsUndone(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        currTask.markAsUndone();
    }

    /**
     * Finds tasks with description containing a keyword.
     *
     * @param word Keyword.
     * @return An arrayList containing tasks with the keyword.
     */
    public ArrayList<Task> find(String word) {
        ArrayList<Task> matchedArr = new ArrayList<>();

        for (Task task : taskList) {
            String description = task.getDescription();
            if (description.contains(word)) {
                matchedArr.add(task);
            }
        }
        return matchedArr;
    }
}
