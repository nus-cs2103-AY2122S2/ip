package duke;

/**
 * Representation containing all string representation and methods
 * used in Duke.
 */
public class UI {

    /**
     * String message for marking tasks.
     */
    static final String mark = "Nice! I've marked this task as";

    /**
     * String representation of custom line for Duke.
     */
    static final String line = "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";

    /**
     * Error message to indicate user to provide a number.
     */
    static final String gnum = "Give me a Number.";

    /**
     * Error message to inform user that description is empty.
     */
    static final String gdes = "Description is empty, give me a description.";

    /**
     * Error message to inform user that description is empty.
     */
    static final String invalid = "INVALID Entry man, Try again :-( ";

    /**
     * Prints the intro of Mike with logo
     * and intro paragraph.
     */
    public static void printIntro() {
        String start = "              Greetings! Mike here!\n" +
                "               How can I help you?\n" ;
        String logo =
                "||======||==||======|| !!!! ||====||    //===//======||\n"
                        + "||                  || !!!! ||    ||   //   //       ||\n"
                        + "||  ||==||  ||==||  ||======||    ||==||   //  ||====||\n"
                        + "||  ||  ||  ||  ||  ||      ||            |||  ||====||\n"
                        + "||  ||  ||  ||  ||  ||      ||    ||==||   \\\\  ||====||\n"
                        + "||  ||  ||  ||  ||  ||      ||    ||   \\\\   \\\\       ||\n"
                        + "||==||  ||==||  ||==||======||====||    \\\\===\\\\======||\n";

        System.out.println("\n" + logo + line);
        System.out.println(start + line);
    }

    /**
     * Prints custom line for Duke.
     */
    public static String printLine() {
        return line;
    }

    /**
     * Prints bye message for Duke.
     * @return
     */
    public static String printTerminate() {
        return "Bye yo. Hope I helped you!";
    }

    /**
     * Prints text in between custom lines of Duke.
     *
     * @param text Text to be printed.
     */
    public static String printWithLines(String text) {
        return line + text + line;
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
