package duke.task;

import java.util.ArrayList;

/**
 * Represents a collection of task objects. A <code>TaskList</code> object
 * can be created to store all the different type of task the user has saved.
 */
public class TaskList {
    private ArrayList<Task> taskArr = new ArrayList<>();

    /**
     * Creates a default instance of a TaskList object.
     */
    public TaskList() {
        taskArr = new ArrayList<>();
    }

    /**
     * Creates an instance of a TaskList object.
     *
     * @param taskArr array of tasks.
     */
    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }

    /**
     * Returns the task at given index i.
     *
     * @param i index of the task in the task list.
     * @return task at index i.
     */
    public Task get(int i) {
        return taskArr.get(i);
    }

    /**
     * Adds the given task into the task list.
     *
     * @param task task to be added into the list.
     */
    public void add(Task task) {
        taskArr.add(task);
    }

    /**
     * Removes the given task from the task list.
     *
     * @param task task to be removed from the list.
     */
    public void remove(Task task) {
        taskArr.remove(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return taskArr.size();
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return the empty status of the task list.
     */
    public boolean isEmpty() {
        return taskArr.isEmpty();
    }

    /**
     * Returns the list of task that matches with the search info
     * provided by the user.
     *
     * @param searchInfo the information to look for certain task in the task list.
     * @return a list of task that matches with input description.
     */
    public TaskList search(String searchInfo) {
        TaskList newTaskList = new TaskList();

        for (Task task : taskArr) {
            if (task.description.contains(searchInfo)) {
                newTaskList.add(task);
            }
        }

        return newTaskList;
    }
}
