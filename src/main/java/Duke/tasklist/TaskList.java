package duke.tasklist;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Contains the task list with operation to modify the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * @param taskList list of task the user has.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the task into task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task from the task list.
     *
     * @param taskNum the task number in the list.
     */
    public void deleteTask(int taskNum) {
        taskList.remove(taskNum);
    }

    /**
     * Marks the task in the task list as complete.
     *
     * @param taskNum the task number in the list.
     * @return the task that is mark.
     */
    public Task markTask(int taskNum) {
        Task currTask = taskList.get(taskNum - 1);
        currTask.setChecked();
        return currTask;
    }

    /**
     * Marks the task in the task list as complete.
     *
     * @param taskNum the task number in the list.
     * @return the task that is mark.
     */
    public Task unmarkTask(int taskNum) {
        Task currTask = taskList.get(taskNum - 1);
        currTask.setUnchecked();
        return currTask;
    }

    /**
     * Returns the list of task.
     *
     * @return the list of task.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
