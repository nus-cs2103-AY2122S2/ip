package saitama;

import saitama.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A task list to keep track of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the given task number.
     *
     * @param taskNumber The task number of the task to be marked.
     */
    public void markTask(int taskNumber) {
        Task task = this.get(taskNumber);
        task.markAsDone();
    }

    /**
     * Unmarks the given task number.
     *
     * @param taskNumber The task number of the task to be unmarked.
     */
    public void unmarkTask(int taskNumber) {
        Task task = this.get(taskNumber);
        task.markAsUndone();
    }

    /**
     * Deletes the given task number.
     *
     * @param taskNumber The task number to be deleted.
     */
    public void delete(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    /**
     * Retrieves the given task number.
     *
     * @param taskNumber The task number ot get.
     * @return The given task number.
     */
    public Task get(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int numOfTasks() {
        return taskList.size();
    }

    /**
     * Searches and returns a list of tasks that contains the query.
     *
     * @param query The String to search for in the task.
     * @return A list of tasks that matches the query.
     */
    public List<Task> search(String query) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(query)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the string format of a task list.
     *
     * @return The string format of a task list.
     */
    @Override
    public String toString() {
        int counter = 1;
        String output = "";
        for (Task task : taskList) {
            output +=  counter + "." + task + "\n";
            counter += 1;
        }
        return output.substring(0, output.length() - 1);
    }

    /**
     * Returns the ArrayList format of a task list.
     *
     * @return The ArrayList format of a task list.
     */
    public ArrayList<Task> toArrayList() {
        return this.taskList;
    }
}
