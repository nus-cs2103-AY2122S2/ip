package duke;

/**
 * Representation containing all string representation and methods
 * used in duke.Duke.
 */
public class UI {

    /**String message for marking tasks.*/
    public static final String mark = "Nice! I've marked this task as";
    /**Error message to indicate user to provide a number.*/
    public static final String gnum = "Give me a Number.";
    /**Error message to inform user that description is empty.*/
    public static final String gdes = "Description is empty, give me a description.";
    /**Error message to inform user that description is empty.*/
    public static final String invalid = "INVALID Entry man, Try again :-( ";
    /**Error message to inform user that the search results were none.*/
    public static final String noResult = "We were unable to find anything.";
    /**Error message to inform user that list is empty.*/
    public static final String emptyList = "The list is empty :(";
    /**Error message to inform user that list is empty.*/
    public static final String unKnown = "Detected an unknown entry.";

    /**Prints the intro paragraph of Mike.*/
    public static String printIntro() {
        String start = "Greetings! Mike here!\n" + "How can I help you?\n" ;
        return start;
    }

    /**
     * Prints Termination message for Duke.
     *
     * @return Termination message.
     */
    public static String printTerminate() {
        return "Bye yo. Hope I helped you!";
    }

    /**
     * Prints add message whenever a task is added.
     *
     * @param text Description of task.
     * @param n Index of task.
     */
    public static String printAddMessage(String text, int n) {
        String gotit = "Got it. I've added this task:\n ";
        String now1 = "Now you have ";
        String now2 = " tasks in the list.";
        return gotit + text + "\n" + now1 + (n + 1) + now2;
    }

    /**
     * Prints message that Task has been deleted.
     *
     * @param text description of deleted Task.
     */
    public static String deleteMessage(String text) {
        String deleted = "Noted. I've removed this task:\n";
        return deleted + text;
    }

    /**
     * Prints message that Task has been deleted.
     *
     * @param text description of Marked Task.
     */
    public static String printMarked(String text) {
        return mark + " as done:\n" + text;
    }

    /**
     * Prints message that Task has been deleted.
     *
     * @param text description of Unmarked Task.
     */
    public static String printUnMarked(String text) {
        return mark + " as not done yet:\n" + text;
    }

}
