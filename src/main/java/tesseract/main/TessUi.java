package tesseract.main;

/**
 * Represent the user interface of Tesseract.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class TessUi {
    private static final String INDENT_ONE = "\t";
    private static final String WELCOME_MSG = "Hi fellow! I am Tesseract\n"
            + "I can bring you to wherever you want in the universe\n"
            + "How can I help you?";
    private static final String LIST_STRUCTURE = "Look what I have noted down for you~\n";
    private static final String DELETE_STRUCTURE = "Okies the following task has been removed:\n"
            + INDENT_ONE + "%s" + "\n" + INDENT_ONE
            + "Now you have %d tasks in the list~";
    private static final String ADD_STRUCTURE = "This has been added to your schedule. "
            + "Wish you can finish it on time\n"
            + INDENT_ONE + "%s\n" + INDENT_ONE + "Now you have %d tasks waiting to be finished.";
    private static final String MARK_STRUCTURE = "Wow you have finished a task! Excellent!\n"
            + INDENT_ONE + "%s";
    private static final String UNMARK_STRUCTURE = "Seems like you have successfully undone your done task\n"
            + INDENT_ONE + "%s";
    private static final String FILTER_STRUCTURE = "Here are the tasks filtered by %s:\n"
            + "%s";
    private static final String SCHEDULE_STRUCTURE = "Here are the tasks scheduled on %s:\n"
            + INDENT_ONE + "%s";
    private static final String ARCHIVE_STRUCTURE = "The following task has been archived~\n" + INDENT_ONE;
    private static final String ADMIT_BUG = "I think there's a bug inside me causing me to malfunction..";

    /**
     * Display the welcome message to the user.
     * @return A String of welcome message
     */
    public String sayHi() {
        return WELCOME_MSG;
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
        return LIST_STRUCTURE + msg;
    }

    /**
     * Display the deleted task to the user.
     * @return A String of deleted tasks
     */
    public String deleteTaskRes(String msg, int numOfTask) {
        return String.format(DELETE_STRUCTURE, msg, numOfTask);
    }

    /**
     * Display the added task to the user.
     * @return A String of task added
     */
    public String addTaskRes(String msg, int numOfTask) {
        return String.format(ADD_STRUCTURE, msg, numOfTask);


    }

    /**
     * Display the task marked as done to the user.
     * @return Task that was marked as done
     */
    public String markAsDoneRes(String msg) {
        return String.format(MARK_STRUCTURE, msg);
    }

    /**
     * Display the task marked as undone to the user.
     * @return Task that was marked as undone
     */
    public String markAsUndoneRes(String msg) {
        return String.format(UNMARK_STRUCTURE, msg);
    }

    /**
     * Display the tasks filtered by the condition to the user.
     * @return A String of filtered tasks
     */
    public String filterRes(String condition, String msg) {
        return String.format(FILTER_STRUCTURE, condition, msg);
    }

    public String scheduleRes(String date, String msg) {
        return String.format(SCHEDULE_STRUCTURE, date, msg);
    }

    public String archiveRes(String msg) {
        return ARCHIVE_STRUCTURE + msg;
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
        return ADMIT_BUG;
    }
}
