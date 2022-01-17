import java.util.*;

/**
 * This class encapsulates a list of Tasks input from the user which is stored in an Array List.
 */
public class List {
    private ArrayList<Task> list;//Array List to store the tasks.
    private int maxSize;//the maximum number of tasks possible, defined by requirement.

    /**
     *
     * @param size: The maximum number of tasks possible which corresponds to maxSize.
     */
    public List(int size) {
        this.maxSize = size;
        this.list = new ArrayList<Task>(maxSize);
    }

    /**
     *  This method adds a task to the list and returns a confirmation of the task being added.
     * @param task: the task input from the user.
     * @return: returns the confirmation of the task added to the list back to the user.
     */
    public String add(Task task) {
        list.add(task);
        return "added: " + task.toString();
    }

    /**
     *
     * @return: Returns a string which displays the list of tasks in order of them being added.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            str = str + index + ". " + list.get(i).toString() + "\n";
        }
        return str;
    }
}
