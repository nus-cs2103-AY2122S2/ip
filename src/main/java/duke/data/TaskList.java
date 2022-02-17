package duke.data;

import java.util.ArrayList;

import duke.task.Task;

/**
 * List containing all the Task
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a empty TaskList
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new Task into an existing TaskList
     * @param newTask
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes a Task at a specific index
     * @param index removes item at said index
     * @return Task that was removed
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Gets the task at the specific index of the TaskList
     * @param index
     * @return the Task at that specific index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * @return Gets number of Task in the TaskList
     */
    public int taskLength() {
        return tasks.size();
    }

    /**
     * @return a ArrayList of Task
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Marks the task at index to be done
     * @param index index in the task
     */
    public void markTask(int index) {
        tasks.get(index).setMark(true);
    }

    /**
     * Marks the task at index to be undone.
     * @param index index in the task
     */
    public void unmarkTask(int index) {
        tasks.get(index).setMark(false);
    }

    /**
     * Function that check if a specific task is in the TaskList.
     * @param taskname name of the task.
     * @return boolean depending if it is present in the Tasklist.
     */
    public boolean checkIfPresent(String taskname) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isSameTask(taskname)) {
                return true;
            }
        }
        return false;
    }
}
