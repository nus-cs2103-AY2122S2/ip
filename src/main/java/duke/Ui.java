package duke;

import java.util.ArrayList;

/**
 * Class containing all UI methods
 */
public class Ui {
    public static final String WELCOME_MSG = "Hello! I'm Duke\nWhat can I do for you?\n";
    public static final String EXIT_MSG = "Bye. Hope to see you again soon!\n";
    public static final String MARKED_TASK_MSG = "Nice! I've marked this task as done:\n";
    public static final String UNMARKED_TASK_MSG = "Nice! I've marked this task as NOT done:\n";
    public static final String FOUND_TASKS_MSG = "Here are the matching tasks in your list:\n";
    public static final String TAGGED_TASK_MSG = "Nice! I've tagged this task:\n";

    public static final String TIME_FORMAT = "HHmm";
    public static final String DATE_TIME_FORMAT = "d/MM/yyyy HHmm";

    public static final String INVALID_COMMAND_ERROR = "Invalid command :( Enter a valid command!";
    public static final String EMPTY_TASK_LIST_ERROR = "Cannot render an empty task list!";
    public static final String INVALID_INDEX_ERROR = "Task index given does not exist! try again.";
    public static final String EVENT_INVALID_TIMINGS_ERROR = "Event must contain both start & end timings!";
    public static final String DEADLINE_INVALID_TIMINGS_ERROR = "Deadline must contain valid end timing!";
    public static final String TASK_INVALID_NAME_ERROR = "Task must contain valid name/description!";
    public static final String INVALID_SEARCH_TERM = "Search term must contain valid name/description!";
    public static final String SEARCH_TERM_NOT_FOUND = "No tasks found that contains the provided search term!";
    public static final String INVALID_TAG_TERM = "Tag command must contain valid task ID and tag term!";
    public static final String EMPTY_TAG_TERM = "Tag command must contain non-empty tag term!";

    /**
     * UI Constructor
     */
    public Ui() {

    }

    /**
     * Returns a styled welcome message upon launch
     */
    public static String displayWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        return WELCOME_MSG;
    }

    /**
     * Returns a styled exit message on exit
     */
    public static String displayExitMsg() {
        return EXIT_MSG;
    }

    /**
     * Returns invalid command message
     */
    public static String displayInvalidCommandError() {
        return INVALID_COMMAND_ERROR;
    }

    /**
     * Render tags of task into a single string
     *
     * @param tags Arraylist of tags associated w task
     * @return String of tags
     */
    public static String renderTags(ArrayList<String> tags) {
        boolean hasTags = tags.size() > 0;

        if (!hasTags) {
            return "";
        }

        String renderStr = " [";
        for (int i = 0; i < tags.size(); i++) {
            boolean isLastTag = i == tags.size() - 1;
            String tag = tags.get(i);

            renderStr += tag;

            if (!isLastTag) {
                renderStr += ", ";
            } else {
                renderStr += "]";
            }
        }
        return renderStr;
    }

    /**
     * Returns list of tasks provided in arraylist as a single string
     *
     * @param tasks ArrayList of tasks
     * @return List of tasks as a single string
     */
    public static String renderTaskList(ArrayList<Task> tasks) {
        assert tasks != null || tasks.size() <= 0 : EMPTY_TASK_LIST_ERROR;
        String renderStr = "";
        for (int i = 0; i < tasks.size(); i++) {
            String dataStr = String.format("%d. ", i + 1) + tasks.get(i) + " \n";
            renderStr += dataStr;
        }
        return renderStr;
    }

    /**
     * Prints string of stored data as an indexed list
     *
     * @param tasks arrayList of Task data
     */
    public static String displayTaskList(ArrayList<Task> tasks) {
        assert tasks != null || tasks.size() <= 0 : EMPTY_TASK_LIST_ERROR;
        return renderTaskList(tasks);
    }

    /**
     * Prints list of tasks found via FIND command
     *
     * @param foundTasks ArrayList of tasks found via FIND
     */
    public static String displayFoundTaskList(TaskList foundTasks) {
        assert foundTasks != null;
        String foundTaskMsg = FOUND_TASKS_MSG + renderTaskList(foundTasks.getTasks());
        return foundTaskMsg;
    }

    /**
     * Display message after marking task as complete
     *
     * @param taskName Task to be marked as complete
     */
    public static String displayMarkMsg(String taskName) {
        assert taskName != "" : "Cannot display an empty task as marked!";
        String markedTaskMsg = MARKED_TASK_MSG + taskName + "\n";
        return markedTaskMsg;
    }

    /**
     * Display message after marking task as incomplete
     *
     * @param taskName Task to be marked as incomplete
     */
    public static String displayUnmarkMsg(String taskName) {
        assert taskName != "" : "Cannot display an empty task as unmarked!";
        String unmarkTaskMsg = UNMARKED_TASK_MSG + taskName + "\n";
        return unmarkTaskMsg;
    }

    /**
     * Display listed message after listing task
     *
     * @param task Task that was listed
     * @param size Number of tasks in list
     */
    public static String displayListedText(Task task, int size) {
        String output = " Got it. I've added this task:\n   "
                + task.toString()
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        return output;
    }

    /**
     * Display deletion message after deleting task
     *
     * @param deletedTask Task that was deleted
     * @param size Number of tasks remaining in list
     */
    public static String displayDeletedMessage(Task deletedTask, int size) {
        assert deletedTask != null : "Cannot display an empty task as deleted!";
        assert size >= 0 : "Number of tasks in list cannot be negative!";
        String output = " Noted. I've removed this task:\n"
                + "  "
                + deletedTask
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        return output;
    }

    /**
     * Display successfully tagged task
     *
     * @param taggedTask Task successfully tagged
     * @return
     */
    public static String displayTaggedMessage(Task taggedTask) {
        assert taggedTask != null : "Cannot display an empty task as tagged!";

        String output = TAGGED_TASK_MSG + taggedTask.toString() + "\n";
        return output;
    }

    public void displayLoadError() {

    }
}
