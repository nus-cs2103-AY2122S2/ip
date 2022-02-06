package duke.task;

import java.util.ArrayList;

/**
 * Holds the list of tasks and its Create, Retrieve, Update, Delete functions.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object that holds tasks and CRUD functions.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList object that holds tasks and CRUD functions.
     * 
     * @param tasks tasks is the list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "TaskList[TaskList] tasks cannot be null.";

        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds task to list.
     * 
     * @param task task is the task to be added.
     */
    public void add(Task task) {
        assert task != null : "TaskList[add] task cannot be null.";

        this.tasks.add(task);
    }

    /**
     * Gets all tasks.
     * 
     * @return Returns list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks or unmarks a task.
     * 
     * @param isMark isMark represents if user wants to mark a task.
     * @param index index represents which task the user wants to mark.
     * @return Returns the updated task.
     */
    public Task toggleCompleted(boolean isMark, int index) {
        assert index > -1 : "TaskList[toggleCompleted] index must be more than -1.";

        Task updateTask = this.tasks.get(index);

        updateTask.setCompleted(isMark);

        return updateTask;
    }

    /**
     * Deletes the task.
     * 
     * @param index index represents which task the user wants to delete.
     */
    public void delete(int index) {
        assert index > -1 : "TaskList[delete] index must be more than -1.";

        this.tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the list.
     * 
     * @return Returns the number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }
}
