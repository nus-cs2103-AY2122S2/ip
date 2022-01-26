package duke.tasks;

import duke.exceptions.InvalidOperationException;
import duke.ui.UiManager;
import java.util.ArrayList;

/**
 * TaskManager Object that handles storage of Task Objects and changes in state of
 * Task Objects.
 */
public class TaskManager {
    private UiManager um;
    private ArrayList<Task> tasks;
    private String taskList;

    /**
     * Constructs the TaskManager Object.
     *
     * @param manager UiManager object that is used to handle user input and output
     */
    public TaskManager(UiManager manager) {
        this.um = manager;
        this.tasks = new ArrayList<>();
    }

    /**
     * Appends Tasks Objects to the end of the ArrayList.
     * Upon appending, the task description and ArrayList size will be printed.
     *
     * @param t Task Object to be appended to the list
     */
    public void addTask(Task t) {
        tasks.add(t);
        um.printAdd(t, this.size());
    }

    /**
     * Appends Task Objects to the end of the ArrayList.
     * For use only during initialisation of TaskManager with previously saved list.
     *
     * @param t Task Object to be appended to the list
     */
    public void insertTask(Task t) {
        tasks.add(t);
    }

    /**
     * Marks Task Objects at a given index as done.
     * Upon successfully marking the Task Object, the Task description would
     * be printed.
     *
     * @param num Integer used for referencing Task Object
     * @throws InvalidOperationException if Task Object is already marked as done.
     */
    public void mark(Integer num) throws InvalidOperationException {
        Task t = tasks.get(num);
        t.mark();
        um.printMark(t);
    }

    /**
     * Marks Task Objects at a given index as done.
     * For use only during initialisation of TaskManager with previously saved list.
     *
     * @param num Integer used for referencing Task Object
     * @throws InvalidOperationException if Task Object is already marked as done.
     */
    public void labelDone(Integer num) throws InvalidOperationException {
        Task t = tasks.get(num);
        t.mark();
    }

    /**
     * Marks Task Objects at a given index as not done.
     * Upon successfully marking the Task Object, the Task description would
     * be printed.
     *
     * @param num Integer used for referencing Task Object
     * @throws InvalidOperationException if Task Object is already unmarked.
     */
    public void unmark(Integer num) throws InvalidOperationException {
        Task t = tasks.get(num);
        t.unmark();
        um.printUnmark(t);
    }

    /**
     * Marks Task Objects at a given index as not done.
     * For use only during initialisation of TaskManager with previously saved list.
     *
     * @param num Integer used for referencing Task Object
     * @throws InvalidOperationException if Task Object is already unmarked.
     */
    public void labelUndone(Integer num) throws InvalidOperationException {
        Task t = tasks.get(num);
        t.unmark();
    }

    /**
     * Removes the Task Object at the specified position in the Arraylist.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     * Upon deletion, the task description is printed.
     *
     * @param num Index of the element to be removed
     */
    public void delete(int num) {
        String taskDetails = tasks.get(num).toString();
        tasks.remove(num);
        um.printDelete(taskDetails, tasks.size());
    }

    /**
     * Removes the Task Object at the specified position in the Arraylist.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     * For use only during initialisation of TaskManager with previously saved list.
     *
     * @param num Index of the element to be removed
     */
    public void remove(int num) {
        String taskDetails = tasks.get(num).toString();
        tasks.remove(num);
    }

    /**
     * Returns the number of Task Objects in the ArrayList.
     *
     * @return the number of Task Objects in the ArrayList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * @return a String representation of the list of Task Objects
     */
    @Override
    public String toString() {
        String s = um.getLine() + "\n";
        s += "Here's your list, Good Sir:\n";
        for (int i = 0; i < tasks.size(); i++) {
            s += i + 1 + ". "+ tasks.get(i).toString() + "\n";
        }
        s += um.getLine();
        this.taskList = s;
        return s;
    }
}
