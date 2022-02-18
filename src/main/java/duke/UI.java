package duke;

/**
 * Represents a UI class which contains string representation of Mike's responses.
 */
public class UI {

    /** Assertion message to inform an issue with Index. */
    public static final String ASSERTION_INDEX_ISSUE = "Index should be more than or equal to 0.";
    /** Assertion message to inform an issue with command format. */
    public static final String ASSERTION_INVALID_COMMAND_FORMAT = "More or less than 2 elements detected";
    /** Opening message for marking tasks. */
    private static final String MARK = "Sweet! I've marked this task as";
    /** Error message to inform user that file cannot be saved. */
    public static final String ERROR_CANNOT_SAVE = "There appears to be an issue,\nwhen saving the data.";
    /** Error message to inform user that file cannot be read. */
    public static final String ERROR_CANNOT_READ = "There appears to be an issue,\nwhen reading the data.";
    /** Error message to inform user that mike could not be created. */
    public static final String ERROR_CREATION = "Something went wrong.\nMike cannot be created.";
    /** Error message to inform user that list is empty. */
    public static final String ERROR_EMPTY_LIST = "I don't see any tasks on the list\nYou appear to be a free man!";
    /** Error message to inform user that there is description entered. */
    public static final String ERROR_NO_DESCRIPTION = "Can you indicate the description too?";
    /** Error message to inform user to provide a number. */
    public static final String ERROR_NO_NUMBER = "Can you indicate the index of the task\n you are referring to?";
    /** Error message to inform user that the search results returned nothing. */
    public static final String ERROR_NO_RESULT = "I was unable to find anything.";
    /** Error message to inform user that command is invalid. */
    public static final String ERROR_INVALID = "Invalid entry boss.\nTell me something else?";
    /** Error message to inform user that there is an unknown entry. */
    public static final String ERROR_UNKNOWN = "There appears to be an unknown entry.";


    /**
     * Returns the introduction paragraph of Mike.
     */
    public static String printIntro() {
        return "Greetings Boss! Mike here!\nHow can I be at your service?";
    }

    /**
     * Returns the termination message of Mike.
     *
     * @return Termination message.
     */
    public static String printTerminate() {
        return "Hope I helped you!\nProgram Terminated.";
    }

    /**
     * Returns message that task has added to list with details
     * of task and total number of tasks on list.
     *
     * @param text Description of task
     * @param no Number of tasks on list.
     * @return Added message with description of task added and total number of tasks on list.
     */
    public static String printAddMessage(String text, int no) {
        String line = "Got it. I've added this task:\n%s\nNow you have %s tasks in the list.";
        return String.format(line, text, no);
    }

    /**
     * Returns message that task has been deleted with task description.
     *
     * @param text Description of deleted task.
     */
    public static String printDeleteMessage(String text) {
        String deleted = "Noted. I've removed this task from\nboth the list and your life:\n";
        return deleted + text;
    }

    /**
     * Returns message that task has been marked with task description.
     *
     * @param text Description of task.
     * @return Marked message with task description.
     */
    public static String printMarked(String text) {
        return UI.MARK + " done:\n" + text;
    }

    /**
     * Returns message that task has been unmarked with task description.
     *
     * @param text Description of task.
     * @return Unmarked message with task description.
     */
    public static String printUnMarked(String text) {
        return UI.MARK + " not done yet:\n" + text;
    }

    /**
     * Returns message that the input task has been tagged as per tag input.
     *
     * @param text Description of task.
     * @param tag Name of tag.
     * @return Tag message with description of task and name of tag.
     */
    public static String printTag(String text, String tag) {
        return String.format("The following task:\n%s\nhas been tagged as a %s task.", text, tag);
    }
}
