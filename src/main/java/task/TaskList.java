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
        this.Tasks.add(task);
        int index = this.getSize();
        assert index > 0 : "Index must be more than 0.";
        return UI.printAddMessage(this.Tasks.get(index - 1).toString(), index);
    }

    /**
     * Deletes the Task on the list as per input value.
     *
     * @param no Number of Task to be deleted on the list.
     * @return Task deleted message.
     */
    public String delete(int no) {
        return UI.deleteMessage(this.Tasks.remove(no).toString());
    }

    /**
     * Marks the Task on the list as per the input value.
     *
     * @param no Number of Task to be marked on list.
     * @return Task marked message.
     */
    public String mark(int no) {
        this.Tasks.get(no).markAsDone();
        return UI.printMarked(this.Tasks.get(no).toString());
    }

    /**
     * Unmark the Task on the list as per the input value.
     *
     * @param no Number of Task to be unmarked on list.
     * @return Task unmarked message.
     */
    public String unMark(int no) {
        this.Tasks.get(no).markAsUnDone();
        return UI.printUnMarked(this.Tasks.get(no).toString());
    }

    /**
     * Tag the Task on the list as the input value and input string.
     *
     * @param no Number of Task to be tagged.
     * @param str
     * @return Task tagged message.
     */
    public String tag(int no, String str) {
        this.Tasks.get(no).tag(str);
        return UI.printTag(this.Tasks.get(no).toString(), str);
    }

    /**
     * Prints Tasks on the list.
     *
     * @return String representation of Tasks on List.
     * @throws DukeException when list is empty.
     */
    public String printTaskList() throws DukeException {
        return printList(this.Tasks);
    }

    /**
     * Prints the list of tasks which contain the search String.
     *
     * @param str String to search for.
     * @return String representation of search result.
     * @throws DukeException If no result is found.
     */
    public String find(String str) throws DukeException {
        ArrayList<Task> resultList = generateList(str);
        if (resultList.size() != 0) {
            return printList(resultList);
        } else {
            throw new DukeException(UI.ERROR_NO_RESULT);
        }
    }

    /**
     * Generates the list of tasks containing the string input.
     * Used only by find() method.
     *
     * @param str String to search for.
     * @return List of tasks with match, empty list if no match.
     */
    private ArrayList<Task> generateList(String str) {
        ArrayList<Task> resultList = new ArrayList<>();
        for (int m = 0; m < this.Tasks.size(); m++) {
            String in = this.Tasks.get(m).getDescription();
            if (in.equals(str) || scan(in, str)) {
                resultList.add(this.Tasks.get(m));
            }
        }
        return resultList;
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
        for (int n = 0; n < words.length; n++) {
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
     * @throws DukeException If list is empty.
     */
    private String printList(ArrayList<Task> list) throws DukeException {
        int size = list.size();
        String str = "";
        if (size != 0) {
            for (int m = 0; m < size; m++) {
                str+= m + 1 + "." + list.get(m).toString() + "\n";
            }
            return str;
        } else {
            throw new DukeException(UI.ERROR_EMPTY_LIST);
        }
    }
}
