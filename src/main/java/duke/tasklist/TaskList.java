package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList class
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for task list
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor for task list from saved data
     * @param list list of tasks
     */
    public TaskList(ArrayList<Task> list) {
        taskList = list;
    }

    /**
     * add task to list of tasks
     * @param task task to be added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * delete task from list of tasks
     * @param n index of task to be deleted
     */
    public void deleteTask(int n) {
        this.taskList.remove(n);
    }

    /**
     * get task from list of tasks
     * @param n index of task to be returned
     * @return task at specified index
     */
    public Task getTask(int n) {
        return this.taskList.get(n);
    }

    /**
     * return size of task list
     * @return size of taks list
     */
    public int getSize() {
        return this.taskList.size();
    }
}
