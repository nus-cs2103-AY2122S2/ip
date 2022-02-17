package task;

import duke.DukeException;
import duke.UI;
import java.util.ArrayList;

/**
 * Represents a list containing all the tasks and method related to the list.
 */
public class TaskList {

    /** List that contains all tasks. */
    private ArrayList<Task> Tasks;

    /**
     * Instantiates the task list with a list of tasks from the stored data.
     * If no data is present, instantiates the task list with an empty list.
     *
     * @param list List of tasks from stored data.
     */
    public TaskList(ArrayList<Task> list) {
        this.Tasks = list;
    }

    /**
     * Returns the task list in ArrayList representation.
     *
     * @return Task list in ArrayList representation.
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
     * Returns message that task has been added to the task list.
     *
     * @param task Task to be added.
     * @return Message that task has been added to the list.
     */
    public String add(Task task) {
        this.Tasks.add(task);
        int index = this.getSize();
        assert index > 0 : "Index must be more than 0.";
        return UI.printAddMessage(this.Tasks.get(index - 1).toString(), index);
    }

    /**
     * Returns message that task corresponding to input index has been deleted.
     *
     * @param no Index of task.
     * @return Message that task has been deleted with details on task.
     */
    public String delete(int no) {
        return UI.deleteMessage(this.Tasks.remove(no).toString());
    }

    /**
     * Returns message that task corresponding to the input index has been marked.
     *
     * @param no Index of task.
     * @return Message that task has been marked with details on task.
     */
    public String mark(int no) {
        this.Tasks.get(no).markAsDone();
        return UI.printMarked(this.Tasks.get(no).toString());
    }

    /**
     * Returns message that task corresponding to the input index has been unmarked.
     *
     * @param no Index of task.
     * @return Message that task has been unmarked with details on task.
     */
    public String unMark(int no) {
        this.Tasks.get(no).markAsUnDone();
        return UI.printUnMarked(this.Tasks.get(no).toString());
    }

    /**
     * Returns message that the task corresponding to the input index has
     * been tagged as per input tag.
     *
     * @param no Index of task to be tagged.
     * @param str Label of tag.
     * @return Message that task has been tagged.
     */
    public String tag(int no, String str) {
        this.Tasks.get(no).tag(str);
        return UI.printTag(this.Tasks.get(no).toString(), str);
    }

    /**
     * Returns list with all tasks on the task list.
     *
     * @return List of tasks on task list.
     * @throws DukeException If no task is on the list.
     */
    public String printTaskList() throws DukeException {
        return printList(this.Tasks);
    }

    /**
     * Returns the list of tasks which contains the search string.
     *
     * @param str String to search for.
     * @return Search result.
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
     * @return List of tasks with match, else empty list.
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
     * Returns true if a string is present in sentence and false
     * if the string is not.
     *
     * @param sentence Sentence to be searched.
     * @param str String input to search against.
     * @return Outcome of search. If String is present return true, else false.
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
     * Returns the string representation of the list with all tasks in the task list.
     *
     * @param list Task list.
     * @return String representation of the list of tasks.
     * @throws DukeException If list is empty.
     */
    private String printList(ArrayList<Task> list) throws DukeException {
        int size = list.size();
        String str = "Your list has " + size + " tasks:\n";
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
