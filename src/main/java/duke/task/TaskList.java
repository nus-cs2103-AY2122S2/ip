package duke.task;
import java.util.ArrayList;

/**
 * A class to contain all tasks of the user. 
 */
public class TaskList {
    
    private ArrayList<Task> taskList;

    /**
     * Initializes the current taskList with an external ArrayList. 
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * A getter method to return the current taskList's size. 
     * @return int representing the current taskList's size. 
     */
    public int size() {
        return taskList.size();
    }

    /**
     * A getter method to return if the current taskList is empty. 
     * @return boolean representing if the current taskList is empty. 
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * A getter method to return the task at an index. 
     * @param index int representing the index of the task to be retrieved. 
     * @return the task at the specified index. 
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * A getter method to remove the task at an index. 
     * @param index int representing the index of the task to be removed. 
     * @return the task at the specified index. 
     */
    public Task remove(int index) {
        return taskList.remove(index);
    }

    /**
     * A setter method to add a task at an index. 
     * @param task Task representing the task to be added. 
     * @return boolean indicating if the task was added successfully or not. 
     */
    public boolean add(Task task) {
        return taskList.add(task);
    }

    /**
     * A getter method to return the current entire taskList. 
     * @return ArrayList containing existing tasks. 
     */
    protected ArrayList<Task> getTasks() {
        return taskList;
    }

}
