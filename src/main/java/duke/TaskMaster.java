package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Handles the overarching list of tasks the user has noted down.
 */
public class TaskMaster {

    ArrayList<Task> tasks;

    /**
     * Constructor
     * @param tasks an arraylist of tasks
     */
    public TaskMaster(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * adds the given task to the stored list of tasks
     * @param t Task t to be added
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Deletes the task from the list of tasks, with index position i - 1
     * @param i int representing the index - 1 to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int i) {
        return this.tasks.remove(i - 1); //-1 because arr index starts frm 0
    }

    /**
     * retrieves all the tasks.
     * @return the tasks as an arraylist.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * finds all tasks that have details that are related to the keyword.
     * @param keyword String to be matched on.
     * @return an arraylist of matching tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDetails().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
