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
     * @return true if the task is added. Else false.
     */
    public boolean addTask(Task task) {
        boolean hasAdded = true;
        for (Task currTask : taskList) {
            if (currTask.taskEquals(task)) {
                hasAdded = false;
            }
        }
        if (hasAdded) {
            taskList.add(task);
        }
        return hasAdded;
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
        currTask.setMarked();
        return currTask;
    }

    /**
     * Marks the task in the task list as not complete.
     *
     * @param taskNum the task number in the list.
     * @return the task that is mark.
     */
    public Task unmarkTask(int taskNum) {
        Task currTask = taskList.get(taskNum - 1);
        currTask.setUnmarked();
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

    /**
     * Finds the relevant tasks based on input.
     *
     * @param input input of the keyword by user.
     * @return the list of result that match the input.
     */
    public ArrayList<Task> findTasks(String input) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(input.toLowerCase())) {
                temp.add(task);
            }
        }
        return temp;
    }
}
