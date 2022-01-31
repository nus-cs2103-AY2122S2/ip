package duke.helper;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents an abstraction for a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();


    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the ith task in the taskList.
     * @param i index of the task in the arrayList.
     * @return The ith task in the taskList.
     */
    public Task get(int i){
        return taskList.get(i);
    }

    /**
     * Returns the entire taskList
     * @return returns the entire taskList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a task to the taskList.
     * @param task The <code>Task</code> object to be added.
     */
    public void insert(Task task){
        taskList.add(task);
    }

    /**
     * Removes a task from the taskList.
     * @param i The index of the task to be deleted.
     */
    public void delete(int i){
        taskList.remove(i);
    }

    /**
     * Returns the size of the TaskList object.
     * @return returns the size of the TaskList object.
     */
    public int size(){
        return taskList.size();
    }

}
