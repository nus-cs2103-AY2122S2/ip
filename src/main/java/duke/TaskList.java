package duke;

import java.util.ArrayList;

/**
 * Represents all the tasks that the current user has.
 */
public class TaskList {

    ArrayList<Task> taskArrayList;

    /**
     * Constructor to create a new instance of a TaskList.
     * @param taskArrayList an ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * To delete a task in the list of tasks.
     * @param index the index of the task to be deleted.
     */
    public void deleteTask(int index) {
        taskArrayList.remove(index);
    }

    /**
     * To add a task into the list of tasks of the user.
     * @param task the object task that has been created.
     */
    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Convert all the tasks into an ArrayList of String, formatted to print
     * for users to see.
     *
     * @return an ArrayList of String to output to the user interface.
     */
    public ArrayList<String> convertListToString() {
        ArrayList<String> stringOfTasks = new ArrayList<>();
        int counter = 1;
        String indentation = "    ";

        if (taskArrayList.isEmpty()) {
            stringOfTasks.add("List is currently empty");
            return stringOfTasks;
        }

        for (Task task: taskArrayList) {
            stringOfTasks.add(indentation + String.valueOf(counter) + ". "  + task.toString() + task.getStatus() + " " + task.getDescription());
            counter++;
        }
        return stringOfTasks;
    }

    /**
     * To unmark a task that is completed.
     * @param index the index of the task that user wants to unmark.
     */
    public void unmarkTask(int index) {
        taskArrayList.get(index).unmarkDone();
    }

    /**
     * To mark a task that is completed.
     * @param index the index of the task that user wants to unmark.
     */
    public void markTask(int index) {
        taskArrayList.get(index).markDone();
    }

    /**
     * To retrieve the task
     * @param index the index of the task to be retrieved.
     * @return the task that is requested.
     */
    public Task getTask(int index) {
        return taskArrayList.get(index);
    }

    /**
     * To check the number of tasks in the list.
     * @return an integer of the number of tasks.
     */
    public int getSize() {
        return taskArrayList.size();
    }
}
