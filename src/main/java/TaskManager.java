import Tasks.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * TaskManager that manages all the tasks that we log in the chatbot.
 */

public class TaskManager {
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Initialises the taskManager
     */
    public TaskManager() {
        storage = new Storage();
        try {
            tasks = storage.readTasks();
        } catch (FileNotFoundException e) {
            //duke.txt dosent exist, so create fresh arrayList.
            tasks = new ArrayList<Task>();
        }
        this.storage = new Storage();

    }

    /**
     * Add a task to the task manager.
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * remove a task from the task manager.
     * @param index index to remove, indexed from 1.
     * @return the task that was removed.
     */
    public Task removeTask(int index) {
        return tasks.remove(index - 1);
    }

    /**
     * return the total number of tasks in the task manager list.
     * @return total number of tasks in the task manager list.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Complete the task at the given index of the task manager (indexed from 1 where 1 is first task).
     * @param index
     * @return the task we completed.
     */
    public Task completeTask(int index) {
        tasks.get(index - 1).complete();
        return tasks.get(index - 1);
    }

    /**
     * Uncomplete the task at the given index of the task manager (indexed from 1 where 1 is first task).
     * @param index
     * @return the task we made incomplete.
     */
    public Task removeCompletedStatusOfTask(int index) {
        tasks.get(index - 1).removeCompletedStatus();
        return tasks.get(index - 1);
    }

    /**
     * Return a nice printable numbered list of the tasks.
     * @return a String representing the tasks in nice format
     */
    public String getPrintableListOfTasks() {
        StringBuilder str = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            str.append(String.valueOf(i) + "." + task.toString() + "\n");
            i++;
        }
        return str.toString();
    }

}
