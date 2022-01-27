package duke;
import java.util.ArrayList;

/**
 * Tracking of all tasks added
 * Represents an ArrayList of Task
 * All operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> all_tasks;

    /**
     * Constructor
     * Create new TaskList
     */
    public TaskList() {
        this.all_tasks = new ArrayList<Task>();
    }

    /**
     * Constructor
     * Create new TaskList from existing list
     * @param currentTasks ArrayList of existing tasks
     */
    public TaskList(ArrayList<Task> currentTasks) {
        this.all_tasks = currentTasks;
    }

    /**
     * Getter to get the ArrayList of Task
     * @return ArrayList of Task
     */
    public ArrayList<Task> getAllTasks() {
        return this.all_tasks;
    }

    /**
     * Adder to add a task to the ArrayList of Task
     * @param task
     */
    public void addTask(Task task) {
        this.all_tasks.add(task);
    }

    /**
     * Deleter to remove a task from the ArrayList of Task by its index
     * @param ranking ranking is index + 1
     */
    public void delete(int ranking) {
        assert ranking > 0 && ranking <= this.all_tasks.size():
                "Please give a valid input (0 < input < number of tasks";
        this.all_tasks.remove(ranking-1);
    }

    /**
     * Updater to mark a task from the ArrayList of Task as done by its index
     * @param ranking
     */
    public void markDone(int ranking) {
        this.all_tasks.get(ranking-1).markDone();
    }

    /**
     * Updater to unmark a task from the ArrayList of Task by its index
     * @param ranking
     */
    public void markUndone(int ranking) {
        this.all_tasks.get(ranking-1).unMarkDone();
    }

    /**
     * Getter to get the length of current ArrayList of Task
     * @return int length of Task ArrayList
     */
    public int getLength() {
        return this.all_tasks.size();
    }
}
