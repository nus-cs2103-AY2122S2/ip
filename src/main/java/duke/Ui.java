package duke;

import duke.task.Task;

/**
 * Responsible for reading user input and to print messages on the command line.
 */
public class Ui {

    //todo: Put these strings in another class?
    public static final String BYE_USAGE = "bye";
    public static final String DEADLINE_USAGE = "deadline [description] /by [yyyy-mm-dd]";
    public static final String DELETE_USAGE = "delete [taskNumber]";
    public static final String EVENT_USAGE = "event [description] /at [time]";
    public static final String FIND_USAGE = "find [keyword]";
    public static final String LIST_USAGE = "list";
    public static final String MARK_USAGE = "mark [taskNumber]";
    public static final String TODO_USAGE = "todo [description]";
    public static final String UNMARK_USAGE = "unmark [taskNumber]";


    public static final String BYE_DESCRIPTION = "Exit the program.";
    public static final String DEADLINE_DESCRIPTION = "Create a new task with a deadline.";
    public static final String DELETE_DESCRIPTION = "Delete the task corresponding to taskNumber";
    public static final String EVENT_DESCRIPTION = "Create a new event with a start and/or end time.";
    public static final String FIND_DESCRIPTION = "Search your list for tasks that match the filter.";
    public static final String LIST_DESCRIPTION = "List all tasks.";
    public static final String MARK_DESCRIPTION = "Mark the task corresponding to taskNumber.";
    public static final String TODO_DESCRIPTION = "Create a new task.";
    public static final String UNMARK_DESCRIPTION = "Unmark the task corresponding to taskNumber.";

    /**
     * Prints a welcome message to the user.
     */
    public static String greet() {
        return "Hello bro.\nWhat can I do for you bro?";
    }

    /**
     * Informs the user that there was an error with loading the list of tasks at {PROJECT_ROOT}/data/duke.txt
     */
    public static String showLoadingError() {
        return "Something went wrong while loading data/duke.txt bro.";
    }

    /**
     * Prints the error message to the command line.
     *
     * @param message The error message to be printed.
     */
    public static String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Notifies the user upon task successfully added and prints the number of tasks in the list.
     *
     * @param task The task added.
     * @param count The total number of tasks in the list.
     */
    public static String showAddTask(Task task, int count) {
        return "Got it bro. I've added this task:\n\t"
               + task
               + "\nNow you have " + count + " tasks in the list.";
    }

    /**
     * Notifies the user upon task successfully deleted and prints the number of tasks in the list.
     *
     * @param task The task deleted.
     */
    public static String showDeleteTask(Task task) {
        return "Deleted this task:\n" + task;
    }

    /**
     * Prints a goodbye message to the user.
     */
    public static String sayBye() {
        return "Bye bro, see you again soon.";
    }

    /**
     * Prints all the tasks in the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public static String showTasks(TaskList tasks) {
        return "Here are your tasks bro:\n" + tasks;
    }

    /**
     * Informs the user that there are no tasks.
     */
    public static String showNoTasks() {
        return "There are no tasks yet, bro.";
    }

    /**
     * Notifies the user upon task successfully marked.
     *
     * @param task The marked task.
     */
    public static String showMarkTask(Task task) {
        return "Ok bro, marked this task:\n" + task;
    }

    /**
     * Notifies the user upon task successfully unmarked.
     *
     * @param task The unmarked task.
     */
    public static String showUnmarkTask(Task task) {
        return "Ok bro, unmarked this task:\n" + task;
    }

    /**
     * Returns the user the tasks matching the search keyword.
     *
     * @param results The filtered tasks.
     */
    public static String showFindTasks(TaskList results) {
        return "Here are the matching tasks in your list:\n" + results;
    }

    /**
     * Returns a string informing the user that there are no tasks found.
     *
     * @param searchTerm The search term.
     */
    public static String showFindNoTasks(String searchTerm) {
        return "There are no tasks with the search term \"" + searchTerm + "\" bro.";
    }

    /**
     * Returns a string stating all the commands
     *
     */
    public static String showHelpPage() {
        return "List of commands:\n"
                + String.format("%s: %s\n", BYE_USAGE, BYE_DESCRIPTION)
                + String.format("%s: %s\n", DEADLINE_USAGE, DEADLINE_DESCRIPTION)
                + String.format("%s: %s\n", DELETE_USAGE, DELETE_DESCRIPTION)
                + String.format("%s: %s\n", EVENT_USAGE, EVENT_DESCRIPTION)
                + String.format("%s: %s\n", FIND_USAGE, FIND_DESCRIPTION)
                + String.format("%s: %s\n", LIST_USAGE, LIST_DESCRIPTION)
                + String.format("%s: %s\n", MARK_USAGE, MARK_DESCRIPTION)
                + String.format("%s: %s\n", TODO_USAGE, TODO_DESCRIPTION)
                + String.format("%s: %s\n", UNMARK_USAGE, UNMARK_DESCRIPTION);
    }
}
