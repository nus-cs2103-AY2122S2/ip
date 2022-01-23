package saitama;

import saitama.tasks.Task;

import java.util.ArrayList;

/**
 * A task list to keep track of tasks.
 */
public class TaskList {

    private String filePath;
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
        System.out.println("OK...");
        System.out.println("The following task has been added to the list: ");
        System.out.println(task);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
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
        System.out.println("OK...");
        System.out.println("The following task has been removed from the list: ");
        System.out.println(taskList.get(taskNumber - 1));
        taskList.remove(taskNumber - 1);
        System.out.println("Now you have " + this.numOfTasks() + " tasks in the list.");
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
     * Prints the list of tasks currently in the task list.
     */
    public void list() {
        if (numOfTasks() == 0) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println(this);
        }
    }

    /**
     * Returns the string format of a task list.
     *
     * @return The string format of a task list.
     */
    @Override
    public String toString() {
        int counter = 1;
        String output = "Here are the tasks in your list:\n";
        for (Task task : taskList) {
            output += counter + "." + task + "\n";
            counter += 1;
        }
        return output.substring(0,output.length() - 1);
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
