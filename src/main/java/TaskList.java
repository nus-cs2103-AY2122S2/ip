import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates a TaskList, that contains up to 100 tasks.
 *
 * @author Ong Han Yang
 */
public class TaskList<T> {
    /** The container for the items, implemented as an ArrayList*/
    private ArrayList<T> list;

    /**
     * Constructor for a TaskList.
     */
    public TaskList() {
        list = new ArrayList<T>();
    }

    /**
     * Method to add an item into the Task List
     * @param item the item to be added.
     */
    public void add(T item) {
        list.add(item);
    }

    /**
     * Overloaded method to add multiple items at a time.
     * @param items all the items to be added.
     */
    public void add(T... items) {
        list.addAll(List.of(items));
    }

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
