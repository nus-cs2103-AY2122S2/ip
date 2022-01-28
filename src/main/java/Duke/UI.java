package Duke;

/**
 * Representation containing all string representation and methods
 * used in Duke.
 */
public class UI {

    /**
     * Staring message for marking tasks.
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
    public static void intro() {
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
    public static void line() {
        System.out.println(line);
    }

    /**
     * Prints bye message for Duke.
     */
    public static void terminate() {
        System.out.println(line + "Bye yo. Hope I helped you!" + line);
    }

    /**
     * Prints text in between custom lines of Duke.
     *
     * @param text Text to be printed.
     */
    public static void printWithLines(String text) {
        System.out.println(line + text + line);
    }

    /**
     * Prints add message whenever a task is added.
     *
     * @param text Description of task.
     * @param n Index of task.
     */
    public static void printAddMessage(String text, int n) {
        String gotit = "Got it. I've added this task:\n ";
        String now1 = "Now you have ";
        String now2 = " tasks in the list.";
        System.out.println(line + gotit + text + "\n" + now1 + (n + 1) + now2 + line);
    }

    /**
     * Prints message that Task has been deleted.
     *
     * @param text description of deleted Task.
     */
    public static void deleteMessage(String text) {
        String deleted = "Noted. I've removed this task:\n";
        System.out.println(line + deleted + text + line);
    }

    /**
     * Prints message that Task has been deleted.
     *
     * @param text description of Marked Task.
     */
    public static void printMarked(String text) {
        System.out.println(line + mark + " as done:\n" + text + line);
    }

    /**
     * Prints message that Task has been deleted.
     *
     * @param text description of Unmarked Task.
     */
    public static void printUnMarked(String text) {
        System.out.println(line + mark + " as not done yet:\n" + text + line);
    }

}
