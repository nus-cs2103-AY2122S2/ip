package paggro.ui;

import java.util.ArrayList;

import paggro.task.Task;

/**
 * This class encapsulates the User Interface of the PaggroBot.
 */
public class Ui {
    private static final String FOUR_SPACE = "    ";


    /**
     * Constructor of the Ui object.
     */
    public Ui () {

    }

    /**
     * Returns the goodbye message.
     *
     * @return String of the message.
     */
    public String showGoodbye() {
        return FOUR_SPACE + "Oh finally. Please don't come back anytime soon. =.=\n";
    }

    /**
     * Returns the given error message.
     *
     * @param msg The error message to be printed.
     * @return String of the message.
     */
    public String showError(String msg) {
        return msg + "\n";
    }

    /**
     * Returns a String of each task in the given list of tasks.
     *
     * @param tasks The given list of tasks.
     * @return String of the tasks.
     */
    @SuppressWarnings("checkstyle:Regexp")
    public String showList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return FOUR_SPACE + "Nothing to look at here... =.=";
        }
        StringBuilder list = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            list.append(FOUR_SPACE + (i + 1) + "." + task + "\n");
        }
        return list.toString();
    }

    /**
     * Returns the empty date message.
     *
     * @return String of the message.
     */
    public String showEmptyDate() {
        return FOUR_SPACE + "Nothing happening on this date... =.=\n";
    }

    /**
     * Returns the marked task message.
     *
     * @param task Task that was marked.
     * @return String of the message.
     */
    public String showMarked(Task task) {
        return FOUR_SPACE + "You've finished this task. Good for you... =.=\n      "
                + task + "\n";
    }

    /**
     * Returns the unmarked task message.
     *
     * @param task Task that was unmarked.
     * @return String of the message.
     */
    public String showUnmarked(Task task) {
        return FOUR_SPACE + "Marked undone. Stop slacking off... =.=\n      "
                + task + "\n";
    }

    /**
     * Returns the deleted task message.
     *
     * @param task Task that was deleted.
     * @return String of the message.
     */
    public String showDeleted(Task task) {
        return FOUR_SPACE + "Fine. I've removed this task:\n      "
                + task.toString() + "\n";
    }

    /**
     * Returns the added task message.
     *
     * @param task Task that was added.
     * @return String of the message.
     */
    public String showAdded(Task task) {
        return FOUR_SPACE + "Fine I've added this task in:\n      "
                + task + "\n";
    }

    /**
     * Returns the given size of task list.
     *
     * @param size Size of the task list.
     * @return String of the message.
     */
    public String showNumber(int size) {
        if (size == 1) {
            return FOUR_SPACE + "Now you have 1 task in the list. =.=\n";
        } else {
            return FOUR_SPACE + "Now you have " + size + " tasks in the list. =.=\n";
        }
    }

    /**
     * Returns the tagged task message.
     *
     * @param task Task that was tagged.
     * @return String of the message.
     */
    public String showTagged(Task task) {
        return FOUR_SPACE + "Fine. I've tagged this task:\n      "
                + task + "\n";
    }

    /**
     * Returns the empty tag message.
     *
     * @return String of the message.
     */
    public String showEmptyTag() {
        return FOUR_SPACE + "No tasks associated with this tag... =.=\n";
    }
}
