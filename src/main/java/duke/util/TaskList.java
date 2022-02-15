package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int totalTasks;

    /**
     * Class constructor specifying list of tasks to be added.
     *
     * @param currentTasks The ArrayList of current tasks.
     */
    public TaskList(ArrayList<Task> currentTasks) {
        this.tasks = currentTasks;
        this.totalTasks = tasks.size();
    }

    /**
     * Class constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.totalTasks = 0;
    }

    /**
     * Returns the current status of the list.
     *
     * @return String stating the number of tasks in the list.
     */
    public String getListStatus() {
        if (this.totalTasks > 1) {
            return "There are " + totalTasks + " tasks in the list now.";
        } else {
            return "There is " + totalTasks + " task in the list now.";
        }
    }

    /**
     * Returns the string representation of the entire list.
     *
     * @return String representation of every task in the list.
     */
    public String getList() {
        String list = "";
        if (totalTasks == 0) {
            list += "There are no tasks in your list.";
        } else {
            list += "Here are the tasks in your list:";
            int index = 1;
            for (int n = 0; n < totalTasks; n++) {
                Task t = tasks.get(n);
                list += "\n" + Ui.showIndent() + index + "." + t.toString();
                index++;
            }
        }
        return list;
    }

    /**
     * Returns the string representation of the list to be stored in hard disk.
     *
     * @return String representation of every task in the list with format: type | isDone | description.
     */
    public String formatListForSaving() {
        String list = "";
        for (Task t: tasks) {
            list = list + t.getType() + " | " + (t.getIsDone() ? "1" : "0") + " | " + t.getDescription() + "\n";
        }
        return list;
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int getTotalTasks() {
        return this.totalTasks;
    }

    /**
     * Adds the given task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
        totalTasks++;
    }

    /**
     * Returns the task deleted from the list.
     *
     * @param taskNum The index of the task in the list before deletion.
     * @return The deleted task.
     */
    public Task delete(int taskNum) {
        Task taskToDelete = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        totalTasks--;
        return taskToDelete;
    }

    /**
     * Returns the task to be marked as done.
     *
     * @param taskNum The index of the task to be marked in the list.
     * @return The marked task.
     */
    public Task mark(int taskNum) {
        Task taskToMark = tasks.get(taskNum - 1);
        taskToMark.markAsDone();
        return taskToMark;
    }

    /**
     * Returns the task to be marked as not done.
     *
     * @param taskNum The index of the task to be marked in the list.
     * @return The unmarked task.
     */
    public Task unmark(int taskNum) {
        Task taskToUnmark = tasks.get(taskNum - 1);
        taskToUnmark.markAsUndone();
        return taskToUnmark;
    }

    /**
     * Returns the string representation of tasks that matches keyword.
     * If no task matches keyword, return not found message.
     *
     * @param keyword The keyword used to search for tasks.
     * @return String representation of matching tasks.
     * @throws DukeException If there are no matching tasks in list.
     */
    public String getTasksWithKeyword(String keyword) throws DukeException {
        String list = "Here are the matching tasks in your list:";
        int count = 0;

        for (int n = 0; n < totalTasks; n++) {
            Task task = tasks.get(n);
            if (task.hasKeyword(keyword)) {
                count++;
                list += "\n" + Ui.showIndent() + count + "." + task;
            }
        }

        if (count > 0) {
            return list;
        } else {
            throw new DukeException("Sorry, there are no tasks that match keyword.");
        }
    }

    /**
     * Returns the task with the updated description.
     *
     * @param taskNum The index of the task to be updated.
     * @param description The new description of the task.
     * @return The updated task.
     */
    public Task update(int taskNum, String description) throws DukeException {
        Task taskToUpdate = tasks.get(taskNum - 1);
        taskToUpdate.updateTask(description);
        return taskToUpdate;
    }
}
