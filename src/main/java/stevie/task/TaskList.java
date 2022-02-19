package stevie.task;

import java.util.ArrayList;

import stevie.exception.TaskException;
import stevie.exception.messages.TaskExceptionMessages;
import stevie.task.types.Task;

/**
 * Represents the a list of Tasks. <code>stevie.task.TaskList</code> object stores and handles users'
 * tasks.
 */
public class TaskList {
    /**
     * Array of Activity in the list
     */
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds and stores task in the task list.
     *
     * @param task a task to be added to task list
     * @return a response string to indicate that task is successfully added
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Searches through task list and retrieve all task that matches a query string.
     *
     * @param query a keyword string to find tasks
     * @return a string representation of all tasks that match query string
     */
    public String find(String query) {
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        for (Task task : tasks) {
            if (task.contains(query)) {
                sb.append(idx++).append(".").append(task.toString()).append("\n");
            }
        }
        if (idx == 1) {
            return "There's no task that matched that keyword!";
        }
        assert sb.length() > 0;
        if (idx > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Marks task in list as done.
     *
     * @param idx index of the task
     * @return the marked task
     * @throws TaskException if index is out of bounds of task list size
     */
    public Task mark(int idx, boolean isDone) throws TaskException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskException(TaskExceptionMessages.InvalidTaskIndexError, (idx + 1));
        }
        Task task = tasks.get(idx);
        if (isDone) {
            task.done();
        } else {
            task.undone();
        }
        return task;
    }

    /**
     * Deletes task in list by index.
     *
     * @param idx index of the task
     * @return response string to user
     * @throws TaskException if index is out of bounds of task list size
     */
    public Task delete(int idx) throws TaskException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskException(TaskExceptionMessages.InvalidTaskIndexError, (idx + 1));
        }
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return task;
    }

    /**
     * Returns a string of the activities added to the list,
     * in order of addition.
     *
     * @return the list of activities if list is not empty
     */
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "Nothing is added yet.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("You have the following upcoming tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        assert sb.length() > 0;
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Get a string that details the amount of task in task list.
     * @return string detailing size of task list
     */
    public String getSize() {
        return "\nYou have " + tasks.size() + " tasks in your list.";
    }

    /**
     * Saves tasks in the task list.
     * @param storage instance of TaskDataHandler that writes tasks to a .txt file
     */
    public void save(TaskDataHandler storage) {
        storage.saveTasks(tasks);
    }

    /**
     * Remove last added task in list.
     */
    public void deleteLast() throws TaskException {
        if (tasks.isEmpty()) {
            throw new TaskException(TaskExceptionMessages.EmptyTaskListError);
        }
        tasks.remove(tasks.size() - 1);
    }

    /**
     * Add task at particular index.
     * @param idx to add task add
     * @param task task to be added
     * @throws TaskException if index is invalid
     */
    public void addAtIndex(int idx, Task task) throws TaskException {
        assert task != null;
        if (idx < 0 || idx > tasks.size()) {
            throw new TaskException(TaskExceptionMessages.InvalidTaskIndexError, (idx + 1));
        }
        tasks.add(idx, task);
    }
}
