package jeff.ui;

/**
 * Ui class is used to interact with the user.
 */
public class Ui {

    private static final String PREFIX = "    ";
    public static String addPrefix(String str) {
        return PREFIX + str;
    }

    /**
     * Return the goodbye message string.
     */
    public String showBye() {
        return "Bye, my name is Jeff";
    }

    public String showList(String str) {
        return str;
    }

    public String showEmptyList() {
        return "Your task list is currently empty.";
    }

    public String showEmptyListFound() {
        return "I cannot find any such entry since your task list is currently empty.";
    }

    public String showNoneFound(String str) {
        return "Sorry but Jeff could not find any task matching the keyword: " + str + "\n";
    }

    public String showFoundList(String str) {
        return "Here are the matching tasks in your list:\n" + str;
    }

    public String showMark(String currTask) {
        return "Nice! I've marked this task as done:\n" + addPrefix(currTask);
    }

    public String showUnmark(String currTask) {
        return "OK, I've marked this task as not done yet:\n" + addPrefix(currTask);
    }

    /**
     * Display the confirmation response when adding a new task.
     *
     * @param currTask current task that was added.
     * @param size amount of tasks in the list.
     * @return Jeff's confirmation response and the size of the list.
     */
    public String showAdded(String currTask, int size) {
        return "Got it. I've added this task:\n" + addPrefix(currTask) + "\n"
                + "Now you have " + size + " task(s) in the list.";
    }

    /**
     * Display the confirmation response when deleting a task.
     *
     * @param currTask task that was deleted.
     * @param size amount of tasks left in the list.
     * @return Jeff's confirmation response and the size of the list.
     */
    public String showDelete(String currTask, int size) {
        return "Noted. I've removed this task:\n" + addPrefix(currTask) + "\n"
                + "Now you have " + size + " task(s) in the list.";
    }

    public String showError(String message) {
        return message;
    }

    /**
     * Display a message showing users where to get help.
     *
     * @return Jeff's response to ask user to read readme.txt
     */
    public String showHelp() {
        return "I do not understand your commands\n"
                + "Please refer to the readme.txt for the available commands\n";
    }
}
