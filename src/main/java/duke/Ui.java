package duke;
/**
 * Provides the format that Duke's response will be displayed */
public class Ui {
    public static final String SEPARATOR = "--------------------------------------\n";
    public static void wrapPrint (String msg) {
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

    public static void printCursor() {
        System.out.println(">>>");
    }
}
