package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The task list which contains all the tasks.
 */
public class TaskList {
    /** The ArrayList which stores all the tasks. */
    private final ArrayList<Task> taskList;

    /**
     * Creates a new Task List.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Marks the task located in the index provided.
     *
     * @param index The index of the task to be marked.
     * @return The string representation of the task that was recently marked.
     * @throws IndexOutOfBoundsException When the user enters the index that is outside the task list.
     */
    public String markTask(int index) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(index - 1);
            task.setDone();
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Unmarks the task indicated by the index in the task list.
     *
     * @param index The index of the task to be unmarked.
     * @return The string representation of the task that was recently unmarked.
     * @throws IndexOutOfBoundsException When the user enters the index that is outside the task list.
     */
    public String unmarkTask(int index) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(index - 1);
            task.setUndone();
            return task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Adds a new task in the task list.
     *
     * @param task The new task to be added into the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Shows all the tasks in the task list.
     */
    public void printTasks() {
        for (int index = 0; index < this.taskList.size(); index++) {
            System.out.println(Integer.toString(index + 1) + ". " + taskList.get(index).toString());
        }
    }

    /**
     * Shows the number of tasks in the task list.
     */
    public void printNoTasks() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    /**
     * Deletes the task indicated by the index.
     *
     * @param index The index of the task to be deleted.
     * @return The string representation of the task that was recently deleted.
     */
    public String deleteFromIndex(int index) {
        String deletedTask = this.taskList.get(index - 1).toString();
        this.taskList.remove(index - 1);
        return deletedTask;
    }

    /**
     * Returns the string format that is stored in the file.
     *
     * @return The string representation that follows the format that is stored in the database.
     */
    public String updateDatabase() {
        String result = "";
        for (Task task: this.taskList) {
            result = result + task.updateIntoDatabase();
        }
        return result;
    }
}