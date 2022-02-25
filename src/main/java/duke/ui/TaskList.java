package duke.ui;

import duke.action.Action;

import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */

public class TaskList {

    private final ArrayList<Action> list = new ArrayList<>();

    /**
     * Empty constructor is triggered if the file location is missing.
     */
    public TaskList() {

    }

    /**
     * Constructs a new TaskList object and
     * updates the current list with data
     * from the previous session.
     *
     * @param storage contains the file from previous session
     */
    public TaskList(Storage storage) {
        ReadFile.readFile(storage.getFile(), list);
    }

    /**
     * Executes the adding of the task
     * onto the list.
     *
     * @param action the task
     */
    public void add(Action action) {
        list.add(action);
    }

    /**
     * Returns the list of tasks.
     *
     * @return list
     */
    public ArrayList<Action> getList() {
        return list;
    }

    /**
     * Returns the size of the list.
     *
     * @return size of list
     */
    public int size() {
        return list.size();
    }

    /**
     * Clears the list of all tasks
     */
    public void clear() {
        list.clear();
    }

    /**
     * Sets one of the list's task's status to done
     * according to the given index from
     * the input.
     *
     * @param taskNo index of task
     */
    public void setDone(int taskNo) throws DukeException {
        try {
            list.set(taskNo, list.get(taskNo).setDone());
        } catch (IndexOutOfBoundsException indexError) {
            throw new DukeException("Please input the right index!");
        }
    }

    /**
     * Unsets one of the list's task's status to undone
     * according to the given index from the input
     *
     * @param taskNo index of task
     */
    public void setUnDone(int taskNo) throws DukeException {
        try {
            list.set(taskNo, list.get(taskNo).setUnDone());
        } catch (IndexOutOfBoundsException indexError) {
            throw new DukeException("Please input the right index!");
        }
    }

    /**
     * Returns one of the list's task based on the
     * given index from the input
     *
     * @param taskNo index of task
     * @return a task
     */
    public Action getAction(int taskNo) {
        return list.get(taskNo);
    }

    /**
     * Returns one of the list's task based on the
     * given index from the input.Also removes it from
     * the list.
     *
     * @param taskNo index of task
     * @return deleted task
     */
    public Action delete(int taskNo) throws DukeException {
        try {
            return list.remove(taskNo);
        } catch (IndexOutOfBoundsException indexError) {
            throw new DukeException("Please input the right index!");
        }
    }

    /**
     * Returns an ordered list of tasks that matches
     * witht the input.
     *
     * @param task input to be matched
     * @return list of matching tasks
     */
    public String findMatching(String task) {
        int notation = 1;
        StringBuilder result = new StringBuilder();
        for (Action action : list) {
            if (action.getTask().contains(task)) {
                result.append(notation).append(".").append(action)
                        .append("\n");
                notation++;
            }
        }
        return result.toString().equals("") ? "Oops, we cannot find a similar task"
                : result.toString();
    }

    /**
     * Prints out the contents of the list
     * in a numeric notation.
     */
    public String listOut() {
        int count = 1;
        StringBuilder toBeAppended = new StringBuilder("Here are the tasks in your list:\n");
        if (list.isEmpty()) {
            toBeAppended = new StringBuilder("Oh, you have nothing to do, how free you are!");
        } else {
            for (Action act : list) {
                toBeAppended.append(count).append(".").append(act).append("\n");
                count++;
            }
        }
        return toBeAppended.toString();
    }
}
