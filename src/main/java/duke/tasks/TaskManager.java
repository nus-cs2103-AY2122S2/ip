package duke.tasks;

import duke.exceptions.InvalidOperationException;
import duke.ui.UiManager;
import java.util.ArrayList;

/**
 * TaskManager Object that handles storage of Task Objects and changes in state of
 * Task Objects.
 */
public class TaskManager {
    private UiManager uiManager;
    private ArrayList<Task> tasks;
    private String taskList;

    /**
     * Constructs the TaskManager Object.
     *
     * @param manager UiManager object that is used to handle user input and output
     */
    public TaskManager(UiManager manager) {
        this.uiManager = manager;
        this.tasks = new ArrayList<>();
    }

    /**
     * Appends Tasks Objects to the end of the ArrayList.
     * Upon appending, the task description and ArrayList size will be printed.
     *
     * @param task Task Object to be appended to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
        uiManager.printAdd(task, this.size());
    }

    /**
     * Appends Task Objects to the end of the ArrayList.
     * For use only during initialisation of TaskManager with previously saved list.
     *
     * @param task Task Object to be appended to the list
     */
    public void insertTask(Task task) {
        tasks.add(task);
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
        Task task = tasks.get(num);
        task.mark();
        uiManager.printMark(task);
    }

    /**
     * Marks Task Objects at a given index as done.
     * For use only during initialisation of TaskManager with previously saved list.
     *
     * @param num Integer used for referencing Task Object
     * @throws InvalidOperationException if Task Object is already marked as done.
     */
    public void labelDone(Integer num) throws InvalidOperationException {
        Task task = tasks.get(num);
        task.mark();
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
        Task task = tasks.get(num);
        task.unmark();
        uiManager.printUnmark(task);
    }

    /**
     * Marks Task Objects at a given index as not done.
     * For use only during initialisation of TaskManager with previously saved list.
     *
     * @param num Integer used for referencing Task Object
     * @throws InvalidOperationException if Task Object is already unmarked.
     */
    public void labelUndone(Integer num) throws InvalidOperationException {
        Task task = tasks.get(num);
        task.unmark();
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
        uiManager.printDelete(taskDetails, tasks.size());
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
        String string = uiManager.getLine() + "\n";
        string += "Here's your list, Good Sir:\n";
        for (int i = 0; i < tasks.size(); i++) {
            string += i + 1 + ". "+ tasks.get(i).toString() + "\n";
        }
        string += uiManager.getLine();
        this.taskList = string;
        return string;
    }
}
