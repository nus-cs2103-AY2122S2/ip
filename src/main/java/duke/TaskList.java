package duke;
import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates new tasklist
     * @param read tasklist obtained by past saved date
     */
    public TaskList(ArrayList<Task> read) {
        this.tasks = new ArrayList<>(read);
    }

    /**
     * Returns this array list
     * @return this array list
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Gets task specified by index
     * @param index of task
     * @return task
     */
    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    /**
     * Removes task from list
     * @param index of task to be removed
     */
    public void removeTask(int idx) {
        tasks.remove(idx);
    }

    /**
     * inserts new task into the list
     * @param task to be inserted into list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        if (!tasks.isEmpty()) {
            StringBuilder out = new StringBuilder();
            tasks.forEach(task -> out.append("\t").append(task.toString()).append("\n"));
            return out.toString();
        } else {
            return "\tEmpty list\n";
        }
    }
}