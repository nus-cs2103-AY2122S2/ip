package aeromon;

import aeromon.task.Task;

import java.util.ArrayList;

/**
 * TaskArrayList class handles the ArrayLists specifically for the Task object types.
 */
public class TaskArrayList {

    private ArrayList<Task> tasks;

    private static final String NO_TASK_MESSAGE = "Nicely! No more tasks on the list! Good job! :)";
    private static final String TASK_MESSAGE = "You currently have %d tasks on the list >.< Jiayous";

    /**
     * Constructs the TaskArrayList object.
     * @param tasks the ArrayList with the task objects.
     */
    public TaskArrayList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs the empty TaskArrayList object.
     */
    public TaskArrayList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a Task to the current TaskArrayList.
     * @param task task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if a Task is already in the current TaskArrayList.
     * @param task task to be checked.
     * @return true if the Task exists, false otherwise.
     */
    public boolean check(Task task) {
        return tasks.contains(task);
    }

    /**
     * Gets the task in the current TaskArrayList according to the index.
     * @param i the index of the Task object to retrieve.
     * @return the respective Task object.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Deleted the task in the current TaskArrayList according to the index.
     * @param i the index of the Task object to delete.
     * @return the deleted Task object.
     */
    public Task delete(int i) {
        return tasks.remove(i);
    }

    /**
     * Gets the ArrayList of Task objects.
     * @return the ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of Tasks in the TaskArrayList.
     * @return the number of Tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the TaskArrayList in the preset String format.
     * @return the Tasks in TaskArrayList in the String format.
     */
    public String getTaskList() {
        StringBuilder list = new StringBuilder();
        int index = 1;

        for (Task task : tasks) {
            list.append(index).append(". ").append(task.toString()).append("\n");
            index++;
        }
        return list.toString();
    }

    /**
     * Gets the current TaskArrayList status.
     * @return the String that shows the number of tasks in the list.
     */
    public String getTasksStatus() {
        if (tasks.size() == 0) {
            return NO_TASK_MESSAGE;
        } else {
            return String.format(TASK_MESSAGE, tasks.size());
        }
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
