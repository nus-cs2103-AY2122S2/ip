package task;

import duke.UI;
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
    public int getSize() {
        return list.size();
    }

    /**
     * Adds Task to the list.
     *
     * @param task Task to be added.
     */
    public String add(Task task) {
        list.add(task);
        return UI.printAddMessage(list.get(getSize()-1).toString(), getSize()-1);
    }

    /**
     * Deletes the Task on the list corresponding to the
     * input value.
     *
     * @param no Number of Task to be deleted.
     */
    public String delete(int no) {
        return UI.deleteMessage(list.remove(no).toString());
    }

    /**
     * Marks the Task on the list corresponding to the
     * input value.
     *
     * @param n Number of Task to be marked.
     */
    public String mark(int n) {
        list.get(n).markAsDone();
        return UI.printMarked(list.get(n).toString());
    }

    /**
     * Unmarks the Task on the list corresponding to the
     * input value.
     *
     * @param n Number of Task to be unmarked.
     */
    public String unMark(int n) {
        list.get(n).markAsUnDone();
        return UI.printUnMarked(list.get(n).toString());
    }

    /**
     * Prints the Tasks in the Task list.
     */
    public String printTaskList() {
        String str = "";
        for(int m = 0; m < list.size(); m++) {
            str+= (m + 1) + "." + list.get(m).toString() +"\n";
        }
        return str;
    }

    public String find(String str) {
        return printSearchList(generateList(str));
    }

    private ArrayList<Task> generateList(String str) {
        ArrayList<Task> output = new ArrayList<>();
        for (int m = 0; m < list.size(); m++) {
            String in = list.get(m).getDescription();
            if (in.equals(str)) {
                output.add(list.get(m));
            } else if (scan(in, str)){
                output.add(list.get(m));
            } else {
                // add nothing
            }
        }
        return output;
    }

    private boolean scan(String in, String str) {
        boolean isPresent = false;
        String[] y = in.split(" ");
        for(int n = 0; n < y.length; n++) {
            if (y[n].equals(str)) {
                isPresent = true;
            }
        }
        return isPresent;
    }

    private String printSearchList(ArrayList<Task> list) {
        String str = "";
        for(int m = 0; m < list.size(); m++) {
            str+= (m + 1) + "." + list.get(m).toString() + "\n";
        }
        return str;
    }
}
