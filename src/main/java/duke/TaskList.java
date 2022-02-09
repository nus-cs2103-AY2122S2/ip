package duke;
import java.util.ArrayList;

/**
 * Tracking of all tasks added
 * Represents an ArrayList of Task
 * All operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> allTasks;

    /**
     * Constructor
     * Create new TaskList
     */
    public TaskList() {
        this.allTasks = new ArrayList<Task>();
    }

    /**
     * Constructor
     * Create new TaskList from existing list
     * @param currentTasks ArrayList of existing tasks
     */
    public TaskList(ArrayList<Task> currentTasks) {
        this.allTasks = currentTasks;
    }

    /**
     * Getter to get the ArrayList of Task
     * @return ArrayList of Task
     */
    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    /**
     * Adder to add a task to the ArrayList of Task
     * @param task
     */
    public void addTask(Task task) {
        allTasks.add(task);
    }

    /**
     * Deleter to remove a task from the ArrayList of Task by its index
     * @param ranking ranking is index + 1
     */
    public void delete(int ranking) {
        assert ranking > 0 && ranking <= allTasks.size():
                "Please give a valid input (0 < input < number of tasks";
        allTasks.remove(ranking-1);
    }

    /**
     * Updater to mark a task from the ArrayList of Task as done by its index
     * @param ranking
     */
    public void markDone(int ranking) {
        allTasks.get(ranking-1).markDone();
    }

    /**
     * Updater to unmark a task from the ArrayList of Task by its index
     * @param ranking
     */
    public void markUndone(int ranking) {
        allTasks.get(ranking-1).unMarkDone();
    }

    /**
     * Getter to get the length of current ArrayList of Task
     * @return int length of Task ArrayList
     */
    public int getLength() {
        return allTasks.size();
    }
}
