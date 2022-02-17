package chibot.tasklist;

import chibot.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the tasks within the program while running.
 */
public class TaskList {

    /** Stores the tasks */
    private final List<Task> tasks;

    /**
     * Instantiates the instance when no data file is found.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiates the instance when taking in a data file.
     *
     * @param savedTasks ArrayList of saved tasks in the data file.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Delete a task from the task list.
     *
     * @param t The task to be deleted.
     */
    public void deleteTask(Task t) {
        this.tasks.remove(t);
    }

    /**
     * Returns the contents of the task list for display to the user.
     *
     * @return String of all tasks in the task list.
     */
    public String getTasksMsg() {
        StringBuilder allTasks = new StringBuilder();
        int taskIndex = 1;
        for (Task t: this.tasks) {
            allTasks.append(taskIndex).append(". ").append(t.toString()).append("\n");
            taskIndex++;
        }
        return allTasks.length() == 0 ? "Chi could not find any tasks, add one to get started nyan!"
                : allTasks.toString();
    }

    /**
     * Returns the contents of the task list for storage into the data file.
     *
     * @return String of all tasks in the task list.
     */
    public String getTaskStore() {
        StringBuilder allTasks = new StringBuilder();
        for (Task t: this.tasks) {
            allTasks.append(t.convertToFileFormat()).append("\n");
        }
        return allTasks.toString();
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task ArrayList.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task in the task list.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns tasks which match the given keywords in proper format.
     *
     * @param wordsToCheck The array of keywords that the task must contain.
     * @return A String of tasks.
     */
    public String checkWordsInTask(String[] wordsToCheck) {
        StringBuilder tasksWithMatchingWords = new StringBuilder();
        for (Task t: this.tasks) {
            if (t.checkDescriptionForWords(wordsToCheck)) {
                tasksWithMatchingWords.append(t).append("\n");
            }
        }
        return tasksWithMatchingWords.toString();
    }
}
