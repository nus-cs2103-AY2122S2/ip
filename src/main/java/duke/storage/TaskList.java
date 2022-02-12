import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> list;

    /**
     * Constructor for TaskList. Initialises an empty ArrayList<Task>
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Add Task to TaskList
     * @param task Task to be added
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Remove Task from TaskList
     * @param index index of Task to be removed
     */
    public void remove(int index) {
        this.list.remove(index);
    }

    /**
     * Get task by index
     * @param index index of Task to be retrieved
     * @return Retrieved Task
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Size of tasklist
     * @return integer size of TaskList
     */
    public int size() {
        return this.list.size();
    }
}
