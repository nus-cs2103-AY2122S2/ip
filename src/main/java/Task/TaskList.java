package Task;

import java.util.List;
import java.util.ArrayList;

/**
 * manage the list of tasks
 */
public class TaskList {
    private List<Task> tasks;
    private int count;

    /**
     * Create a new TaskList class
     */
    public  TaskList () {
        tasks = new ArrayList<Task>();
    }

    /**
     * Create a new TaskList class with input list of Tasks
     *
     * @param inputTks input list of tasks from reading the save file
     */
    public TaskList(List<Task> inputTks) {
        tasks = inputTks;
        count = inputTks.size();
    }

    /**
     * add a new task into the list of task
     *
     * @param tk input task
     */
    public void addTask(Task tk) {
        tasks.add(tk);
        count++;
    }

    /**
     * delete a task in the list of task
     *
     * @param num index + 1 of the task to be deleted
     */
    public void delete(int num) {
        tasks.remove(num -1);
        count--;
    }

    /**
     * mark the task as done
     *
     * @param num index + 1 of the task to be marked
     */
    public void markTask(int num) {
        tasks.get(num - 1).markDone();
    }

    /**
     * mark the task as not done
     *
     * @param num index + 1 of the task to be unmarked
     */
    public void unmarkTask(int num) {
        tasks.get(num - 1).markNotDone();
    }

    /**
     * get the string format of a task
     *
     * @param num index + 1 for the task
     * @return the string format of the task
     */
    public String getTaskStr(int num) {
        return tasks.get(num - 1).toString();
    }

    /**
     * get the total number of task in the tasklist
     *
     * @return a number which is the total number of task in the tasklist
     */
    public int getCount() {
        return count;
    }

    /**
     * return a string with all the task in the task list in string format
     *
     * @return a string with all the task in the task list in string format
     */
    public String listTasks() {
        if (count == 0) {
            return  "You got no task!!";
        }

        String s = "You forgetful baka... here are your tasks: ";

        for (int i = 0; i < count; i++) {
            int num = i + 1;
            s += "\n" + num + ". " + tasks.get(i).toString();
        }

        return s;

    }

    /**
     * convert the list of tasks to string for file IO
     *
     * @return a string of all the tasks in correct format for the save file
     */
    public String tasksToString() {
        String strReturn = "";
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                strReturn = tasks.get(i).saveString();
            } else {
                strReturn += "\n" + tasks.get(i).saveString();
            }
        }
        return strReturn;
    }

    public String tasksFinder(String toFind) {
        String s = "";
        int counter = 1;
        for (int i = 0; i < count; i++) {
            if (tasks.get(i).description.contains(toFind)) {
                s += "\n" + counter + ". " + tasks.get(i).toString();
                counter++;
            }
        }

        return s;
    }

}
