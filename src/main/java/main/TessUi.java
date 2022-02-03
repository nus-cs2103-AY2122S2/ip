package main;
/**
 * Represent the user interface of Tesseract.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class TessUi {
    // deals with interactions with the user
    protected static final String INDENT_ONE = "    ";
    protected static final String INDENT_TWO = "        ";

    /**
     * Display the welcome message to the user.
     * @return A String of welcome message
     */
    public String sayHi() {
        return "Hi fellow! I am Tesseract\n"
                + "I can bring you to wherever you want in the universe\n"
                + "How can I help you?";
    }

    /**
     * Display the farewell message to the user.
     * @return A String of farewell message
     */
    public String sayBye() {
        return "Ok I'm gonna travel to another planet now\n"
                + "Hope to see you again when I'm back :P";
    }

    /**
     * Display the list of tasks to the user.
     * @return A String of all tasks in the queried list
     */
    public String listTasks(String msg) {
        String res = "Look what I have noted down for you~ \n" + msg;
        return res;
    }

    /**
     * Display the deleted task to the user.
     * @return A String of deleted tasks
     */
    public String deleteTaskRes(String msg, int numOfTask) {
        String res = "Okies the following task has been removed:\n"
                + INDENT_TWO + msg + "\n"
                + INDENT_ONE + "Now you have " + numOfTask + " tasks in the list~\n";
        return res;
    }

    /**
     * Display the added task to the user.
     * @return A String of task added
     */
    public String addTaskRes(String msg, int numOfTask) {
        String res = "This has been added to your schedule. Wish you can finish it on time\n"
                + INDENT_TWO + msg + "\n"
                + INDENT_ONE + "Now you have "
                + numOfTask + " tasks waiting to be finished.\n";
        return res;
    }

    /**
     * Display the task marked as done to the user.
     * @return Task that was marked as done
     */
    public String markAsDoneRes(String msg) {
        String res = "Wow you have finished a task! Excellent! \n"
                + INDENT_TWO + msg + "\n";
        return res;
    }

    /**
     * Display the task marked as undone to the user.
     * @return Task that was marked as undone
     */
    public String markAsUndoneRes(String msg) {
        String res = "Seems like you have successfully undone your done task \n"
                + INDENT_TWO + msg + "\n";
        return res;
    }

    /**
     * Display the tasks filtered by the condition to the user.
     * @return A String of filtered tasks
     */
    public String filterRes(String condition, String msg) {
        String res = "Here are the tasks filtered by "
                + condition + ":\n" + msg;
        return res;
    }

    /**
     * Display the error message to the user.
     * @return A String of error
     */
    public String showError(String err) {
        return err;
    }

    /**
     * Display to user the existence of bug(s) in the program.
     * @return A String indicating bugs
     */
    public String admitBug() {
        String res = "I think there's a bug inside me causing me to malfunction..";
        return res;
    }
}
