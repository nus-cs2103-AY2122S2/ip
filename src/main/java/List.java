import java.util.*;

/**
 * This class encapsulates a list of Tasks input from the user which is stored in an Array List.
 */
public class List {
    private ArrayList<Task> arrayList;//Array List to store the tasks.
    private int maxSize;//the maximum number of tasks possible, defined by requirement.

    /**
     *
     * @param size: The maximum number of tasks possible which corresponds to maxSize.
     */
    public List(int size) {
        this.maxSize = size;
        this.arrayList = new ArrayList<Task>(maxSize);
    }

    /**
     * This method marks a task at a specific index in the list as done.
     * @param index: index of the task to be marked done in the array list of list
     * @return: returns a String that verifies that the task is marked as done.
     */
    public String markDone(int index) {
        Task task = arrayList.get(index);
        return task.markDone();
    }

    /**
     * This method marks a task at a specific index in the list as not done.
     * @param index: index of the task to be marked as not done in the array list of list.
     * @return: returns a String that verfies that the task is marked as done.
     */
    public String unmarkDone(int index) {
        Task task = arrayList.get(index);
        return task.unmarkDone();
    }

    /**
     *  This method adds a task to the list and returns a confirmation of the task being added.
     * @param task: the task input from the user.
     * @return: returns the confirmation of the task added to the list back to the user.
     */
    public String add(Task task) {
        arrayList.add(task);
        return "added: " + task.toString();
    }

    /**
     *
     * @return: Returns a string which displays the list of tasks in order of them being added.
     */
    @Override
    public String toString() {
        String str = "Here are the tasks in your list:\n";
        for (int i = 0; i < arrayList.size(); i++) {
            int index = i + 1;
            str = str + index + ". " + arrayList.get(i).toString() + "\n";
        }
        return str;
    }
}
