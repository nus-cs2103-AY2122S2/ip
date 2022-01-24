package duke.helptool;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Task list.
 */
public class TaskList {
    private final ArrayList<Task> TaskList;

    /**
     * Instantiates a new Task list.
     *
     * @param arrayList the array list
     */
    public TaskList(ArrayList<Task> arrayList){
        this.TaskList = Objects.requireNonNullElseGet(arrayList, ArrayList::new);
    }

    /**
     * Instantiates a new Task list.
     */
    public TaskList() {
        this.TaskList = new ArrayList<>();
    }


    /**
     * Add task.
     *
     * @param task the task
     */
    public void addTask(Task task){
        this.TaskList.add(task);
    }

    /**
     * Delete task.
     *
     * @param index the index
     */
    public void delete(int index){
        this.TaskList.remove(index);
    }

    /**
     * Get task at index.
     *
     * @param index the index
     * @return the task
     */
    public Task getTask(int index){
        return this.TaskList.get(index);
    }

    /**
     * Get size int.
     *
     * @return the int
     */
    public int getSize(){
        return this.TaskList.size();
    }


}
