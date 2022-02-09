package task;

import duke.DukeException;
import duke.UI;
import java.util.ArrayList;

/**
 * Represents a list containing all the Tasks.
 */
public class TaskList {

    /**
     * List that contains all Tasks.
     */
    private ArrayList<Task> Tasks;

    /**
     * Creates a TaskList based on an already
     * existing list or empty list of tasks.
     *
     * @param list
     */
    public TaskList(ArrayList<Task> list) {
        this.Tasks = list;
    }

    /**
     * Returns the TaskList in ArrayList representation.
     *
     * @return TaskList in ArrayList representation.
     */
    public ArrayList<Task> getList() {
        return this.Tasks;
    }

    /**
     * Returns the size of the list.
     *
     * @return Number of tasks in TaskList.
     */
    public int getSize() {
        return this.Tasks.size();
    }

    /**
     * Adds Task to the TaskList.
     *
     * @param task Task to be added.
     * @return Added Task Message.
     */
    public String add(Task task) {
        Tasks.add(task);
        int index = this.getSize() - 1;
        assert index >= 0 : "Index must be 0 or more.";
        return UI.printAddMessage(Tasks.get(index).toString(), index);
    }

    /**
     * Deletes the Task on the list as per input value.
     *
     * @param no Number of Task to be deleted on the list.
     * @return Task deleted message.
     */
    public String delete(int no) {
        return UI.deleteMessage(Tasks.remove(no).toString());
    }

    /**
     * Marks the Task on the list as per the input value.
     *
     * @param no Number of Task to be marked on list.
     * @return Task marked message.
     */
    public String mark(int no) {
        Tasks.get(no).markAsDone();
        return UI.printMarked(Tasks.get(no).toString());
    }

    /**
     * Unmark the Task on the list as per the input value.
     *
     * @param no Number of Task to be unmarked on list.
     * @return Task unmarked message.
     */
    public String unMark(int no) {
        Tasks.get(no).markAsUnDone();
        return UI.printUnMarked(Tasks.get(no).toString());
    }

    /**
     * Prints the Tasks in the TaskList.
     */
    public String printTaskList() throws DukeException {
        return printList(this.Tasks);
    }

    /**
     * Prints the list of tasks which contain the search String.
     *
     * @param str String to search for.
     * @return String representation of search result.
     */
    public String find(String str) throws DukeException {
        ArrayList<Task> resultList = generateList(str);
        if (resultList.size() != 0) {
            return printList(resultList);
        } else {
            throw new DukeException(UI.noResult);
        }
    }

    /**
     * Generates the list of tasks containing
     * the string input.
     *
     * @param str String to search for.
     * @return List of tasks if match is present, empty list if no match.
     */
    private ArrayList<Task> generateList(String str) {
        ArrayList<Task> list = new ArrayList<>();
        for(int m = 0; m < Tasks.size(); m++) {
            String in = Tasks.get(m).getDescription();
            if (in.equals(str) || scan(in, str)) {
                list.add(Tasks.get(m));
            }
        }
        return list;
    }

    /**
     * Returns whether if the string input is present in sentence.
     *
     * @param sentence Sentence to be searched.
     * @param str String input.
     * @return Search result. True if String is present in Result.
     */
    private boolean scan(String sentence, String str) {
        boolean isPresent = false;
        String[] words = sentence.split(" ");
        for(int n = 0; n < words.length; n++) {
            if (words[n].equals(str)) {
                isPresent = true;
            }
        }
        return isPresent;
    }

    /**
     * Prints the Tasks in the input List.
     *
     * @param list List of Tasks.
     * @return String Representation of List to be printed.
     */
    private String printList(ArrayList<Task> list) throws DukeException {
        String str = "";
        int size = list.size();
        if (size != 0) {
            for(int m = 0; m < size; m++) {
                str+= (m + 1) + "." + list.get(m).toString() + "\n";
            }
            return str;
        } else {
            throw new DukeException(UI.emptyList);
        }
    }
}
