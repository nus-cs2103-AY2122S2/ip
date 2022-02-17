package duke;

/**
 * Representation containing all string representation and methods
 * used in duke.Duke.
 */
public class UI {

    /**String message for marking tasks.*/
    private static final String MARK = "Nice! I've marked this task as";

    /**Error message to indicate user to provide a number.*/
    public static final String ERROR_NO_NUMBER = "Give me a Number.";
    /**Error message to inform user that description is empty.*/
    public static final String ERROR_NO_DESCRIPTION = "Description is empty, give me a description.";
    /**Error message to inform user that description is empty.*/
    public static final String ERROR_INVALID = "INVALID Entry man, Try again :-( ";
    /**Error message to inform user that the search results were none.*/
    public static final String ERROR_NO_RESULT = "We were unable to find anything.";
    /**Error message to inform user that list is empty.*/
    public static final String ERROR_EMPTY_LIST = "I don't see any tasks on the list.\nYou appear to be a free man!";
    /**Error message to inform user that list is empty.*/
    public static final String ERROR_UNKNOWN = "There appears to be an unknown entry.";

    /**Prints the intro paragraph of Mike.*/
    public static String printIntro() {
        return "Greetings! Mike here!\n How can I help you?";
    }

    /**
     * Prints Termination message for Duke.
     *
     * @return Termination message.
     */
    public static String printTerminate() {
        return "Hope I helped you!\nProgram Terminated.";
    }

    /**
     * Prints add message whenever a task is added.
     *
     * @param text Description of task.
     * @param no Index of task.
     */
    public static String printAddMessage(String text, int no) {
        String line = "Got it. I've added this task:\n%s\nNow you have %s tasks in the list.";
        return String.format(line, text, no);
    }

    /**
     * Prints message that Task has been deleted.
     *
     * @param text description of deleted Task.
     */
    public static String deleteMessage(String text) {
        String deleted = "Noted. I've removed this task from both the list and your life:\n";
        return deleted + text;
    }

    /**
     * Prints message that Task has been deleted.
     *
     * @param text description of Marked Task.
     */
    public static String printMarked(String text) {
        return UI.MARK + " as done:\n" + text;
    }

    /**
     * Prints message that Task has been deleted.
     *
     * @param text description of Unmarked Task.
     */
    public static String printUnMarked(String text) {
        return UI.MARK + " as not done yet:\n" + text;
    }

    /**
     * Prints message that Task has been tagged as per tag input.
     *
     * @param text Description of Task.
     * @param tag Name of Tag.
     * @return String representation of Tag message.
     */
    public static String printTag(String text, String tag) {
        return String.format("The following task:\n%s\nhas been tagged as a %s task.", text, tag);
    }
}
