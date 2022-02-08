package task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks.
 */
public class TaskList {

    private static final int FIRST_INDEX = 0;
    private List<Task> tasks;
    private int count;

    /**
     * Creates a new TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList class with input list of Tasks.
     *
     * @param inputTks input list of tasks from reading the save file.
     */
    public TaskList(List<Task> inputTks) {
        tasks = inputTks;
        count = inputTks.size();
    }

    /**
     * Adds a new task into the list of task.
     *
     * @param tk input task.
     */
    public void addTask(Task tk) {
        tasks.add(tk);
        count++;
    }

    /**
     * Deletes a task in the list of task.
     *
     * @param num index + 1 of the task to be deleted.
     */
    public void delete(int num) {
        tasks.remove(num - 1);
        count--;
    }

    /**
     * Marks the task as done.
     *
     * @param num index + 1 of the task to be marked.
     */
    public void markTask(int num) {
        tasks.get(num - 1).markDone();
    }

    /**
     * Marks the task as not done.
     *
     * @param num index + 1 of the task to be unmarked.
     */
    public void unmarkTask(int num) {
        tasks.get(num - 1).markNotDone();
    }

    /**
     * Gets the string format of a task.
     *
     * @param num index + 1 for the task.
     * @return the string format of the task.
     */
    public String getTaskStr(int num) {
        return tasks.get(num - 1).toString();
    }

    /**
     * Gets the total number of task in the tasklist.
     *
     * @return a number which is the total number of task in the tasklist.
     */
    public int getCount() {
        return count;
    }

    /**
     * Return the first index for the task
     *
     * @return the first index for the task
     */
    public int getFirstIndex() {
        return FIRST_INDEX;
    }

    /**
     * Returns a string with all the task in the task list in string format.
     *
     * @return a string with all the task in the task list in string format.
     */
    public String listTasks() {
        if (count == FIRST_INDEX) {
            return "You got no task!!";
        }

        String s = "You forgetful baka... here are your tasks: ";

        for (int i = FIRST_INDEX; i < count; i++) {
            int num = i + 1;
            s += "\n" + num + ". " + tasks.get(i).toString();
        }

        return s;
    }

    /**
     * Converts the list of tasks to string for file IO.
     *
     * @return a string of all the tasks in correct format for the save file.
     */
    public String tasksToString() {
        String strReturn = "";
        for (int i = FIRST_INDEX; i < count; i++) {
            if (i == FIRST_INDEX) {
                strReturn = tasks.get(i).saveString();
            } else {
                strReturn += "\n" + tasks.get(i).saveString();
            }
        }
        return strReturn;
    }

    /**
     * Finds the tasks that contain keywords input by user.
     *
     * @param toFind the keyword by user to find.
     * @return String of the found tasks.
     */
    public String findTask(String... toFind) {
        String s = "";
        int counter = 1;
        for (int i = 0; i < count; i++) {
            for (String si : toFind) {
                if (tasks.get(i).description.contains(si)) {
                    s += "\n" + counter + ". " + tasks.get(i).toString();
                    counter++;
                }
            }
        }

        return s;
    }

}
