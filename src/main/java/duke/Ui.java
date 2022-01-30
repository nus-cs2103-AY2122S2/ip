package duke;
/**
 * Provides the format that Duke's response will be displayed
 */
public class Ui {
    public static final String SEPARATOR = "--------------------------------------\n";

    /**
     * wraps the message nicely with a bar separator above and below it, and prints it.
     *
     * @param msg the string message to be printed
     */
    public static void wrapPrint(String msg) {
        String[] splitted = msg.split("\n");
        String wrapee = "";
        for (String line : splitted) {
            wrapee = wrapee.concat("    " + line + "\n");
        }
        System.out.println(wrap(wrapee));
    }

    private static String wrap(String msg) {
        return SEPARATOR + msg + SEPARATOR;
    }
}
