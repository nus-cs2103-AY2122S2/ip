package duke;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Represents all the tasks that the current user has.
 */
public class TaskList {

    private ArrayList<Task> taskArrayList;

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
            stringOfTasks.add(indentation + String.valueOf(counter) + ". "
                    + task.toString() + task.getStatus() + " " + task.getDescription());
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
     * To retrieve the ArrayList of tasks.
     * @return the ArrayList of tasks.
     */
    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    /**
     * To check the number of tasks in the list.
     * @return an integer of the number of tasks.
     */
    public int getSize() {
        return taskArrayList.size();
    }

    /**
     * find in the list of tasks with similar names.
     * @param taskName the search enquiry.
     * @return an array list of tasks that contain the search enquiry.
     */
    public ArrayList<String> find(String taskName) {
        String indentation = "    ";
        int counter = 1;

        ArrayList<Task> foundTask = new ArrayList<>();
        for (Task task: taskArrayList) {
            if (task.getDescription().toLowerCase(Locale.ROOT).contains(taskName.toLowerCase(Locale.ROOT))) {
                foundTask.add(task);
            }
        }

        ArrayList<String> stringOfTasks = new ArrayList<>();

        if (foundTask.isEmpty()) {
            stringOfTasks.add("No such task found");
            return stringOfTasks;
        }

        stringOfTasks.add(indentation + "Here are the matching tasks in your list:");
        for (Task task: foundTask) {
            stringOfTasks.add(indentation + String.valueOf(counter) + ". "
                    + task.toString() + task.getStatus() + " " + task.getDescription());
            counter++;
        }

        foundTask.clear();
        return stringOfTasks;
    }

    @Override
    public String toString() {
        String s = new String();
        int counter = 1;
        for (Task task: taskArrayList) {
            s += (String.valueOf(counter) + ". "
                    + task.toString() + task.getStatus() + " " + task.getDescription() + "\n");
            counter++;
        }
        return s;
    }
}

