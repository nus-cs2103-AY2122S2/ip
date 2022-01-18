import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a TaskList, that contains up to 100 tasks.
 *
 * @author Ong Han Yang
 */
public class TaskList {
    /** The container for the items, implemented as an ArrayList*/
    private ArrayList<Task> list;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Method to add an item into the Task List
     * @param item the item to be added.
     */
    public void add(Task item) {
        this.list.add(item);
    }

    /**
     * Overloaded method to add multiple items at a time.
     * @param items all the items to be added.
     */
    public void add(Task... items) {
        this.list.addAll(List.of(items));
    }

    /**
     * Method to mark a task as done or undone.
     *
     * @param taskNum the task number to be marked.
     * @param isDone whether the task is to be done or not.
     */
    public void markTask(int taskNum, boolean isDone) {
        this.list.get(taskNum).markAs(isDone);
    }

    /**
     * Method to display a stored task as a String.
     *
     * @param taskNum the index of the task to be displayed.
     * @return String representation of the task.
     */
    public String displayTask(int taskNum) {
        return this.list.get(taskNum).toString();
    }

    /**
     * Overridden toString method to display the TaskList as a String.
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                // For the last item, don't append a newline character.
                sb.append(String.format("%d. %s", i + 1, list.get(i).toString()));
            } else {
                sb.append(String.format("%d. %s\n", i + 1, list.get(i).toString()));
            }
        }
        return sb.toString();
    }
}
