package stevie.task;

import stevie.exception.TaskException;

import java.util.ArrayList;

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

    public String add(Task task) {
        tasks.add(task);
        return "Got it! I have added a new task:\n" + task
                + "\nYou have " + tasks.size() + " tasks in your list.";
    }

    /**
     * Marks task in list as done.
     *
     * @param idx index of the task
     * @return the marked task
     * @throws TaskException if index is out of bounds of task list size
     */
    public String markDone(int idx) throws TaskException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskException("There is no task with index: " + (idx + 1));
        } else {
            Task ac = tasks.get(idx);
            ac.done();
            return "This activity is marked as done:\n" + ac;
        }
    }

    /**
     * Marks task in list as undone.
     *
     * @param idx index of the task
     * @return the unmarked task
     * @throws TaskException if index is out of bounds of task list size
     */
    public String markUndone(int idx) throws TaskException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskException("There is no task with index: " + (idx + 1));
        } else {
            Task ac = tasks.get(idx);
            ac.undone();
            return "This activity is unmarked as done:\n" + ac;
        }
    }

    /**
     * Deletes task in list by index.
     *
     * @param idx index of the task
     * @return response string to user
     * @throws TaskException if index is out of bounds of task list size
     */
    public String delete(int idx) throws TaskException {
        if (idx < 0 || idx >= tasks.size()) {
            throw new TaskException("There is no task with index: " + (idx + 1));
        } else {
            Task ac = tasks.get(idx);
            tasks.remove(idx);
            return "I have deleted the following task:\n"
                    + ac.toString()
                    + "\nYou have " + tasks.size() + " tasks left.";
        }
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
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public void save(TaskDataHandler storage) {
        storage.saveTasks(tasks);
    }
}
