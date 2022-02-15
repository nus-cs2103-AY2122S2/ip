package aeromon;

import aeromon.task.Task;

import java.util.ArrayList;

/**
 * TaskArrayList class handles the ArrayLists specifically for the Task object types.
 */
public class TaskArrayList {

    private ArrayList<Task> tasks;

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

        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return list.toString();
    }

    /**
     * Gets the current TaskArrayList status.
     * @return the String that shows the number of tasks in the list.
     */
    public String getTasksStatus() {
        if (tasks.size() == 0) {
            return "Nicely! No more tasks on the list! Good job! :)";
        } else {
            return String.format("You currently have %d tasks on the list >.< Jiayous", tasks.size());
        }
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
