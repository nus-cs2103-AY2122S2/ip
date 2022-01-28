package Task;

import Duke.UI;
import java.util.ArrayList;

/**
 * Represents a list housing all the Tasks.
 */
public class TaskList {

    /**
     * List to house the Tasks.
     */
    private ArrayList<Task> list;

    /**
     * Creates a Task list based on an already
     * existing list or empty list.
     *
     * @param list
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the list in ArrayList representation.
     *
     * @return List in ArrayList representation.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the size of the list.
     *
     * @return size of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds Task to the list.
     *
     * @param task Task to be added.
     * @param n Index of Task added.
     */
    public void add(Task task, int n) {
        list.add(task);
        UI.printAddMessage(list.get(n).toString(), n);
    }

    /**
     * Deletes the Task on the list corresponding to the
     * input value.
     *
     * @param no Number of Task to be deleted.
     */
    public void delete(int no) {
        UI.deleteMessage(list.remove(no).toString());
    }

    /**
     * Marks the Task on the list corresponding to the
     * input value.
     *
     * @param n Number of Task to be marked.
     */
    public void mark(int n) {
        list.get(n).markAsDone();
        UI.printMarked(list.get(n).toString());
    }

    /**
     * Unmarks the Task on the list corresponding to the
     * input value.
     *
     * @param n Number of Task to be unmarked.
     */
    public void unMark(int n) {
        list.get(n).markAsUnDone();
        UI.printUnMarked(list.get(n).toString());
    }

    /**
     * Prints the Tasks in the Task list.
     */
    public void printTaskList() {
        UI.line();
        for(int m = 0; m < list.size(); m++) {
            System.out.println((m + 1) + "." + list.get(m).toString());
        }
        UI.line();
    }
}
