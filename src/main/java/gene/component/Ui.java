package gene.component;

/**
 * The Ui class. handles all of gene's user interactions.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class Ui {
    private final static String GREETING = "Hello! I'm ";
    private final static String LOGO = " GGGG                      \n"
            + "G    G   eeee   n nnn    eeee \n"
            + "G       e    e  nn   n  e    e\n"
            + "G  GGG  eeeeee  n    n  eeeeee\n"
            + "G    G  e       n    n  e     \n"
            + "G    G  e    e  n    n  e    e\n"
            + " GGGG    eeee   n    n   eeee ";
    private final static String GOODBYE = "Bye. Hope to see you again soon!";

    /**
     * Print input, wrapper for system.out.print
     *
     * @param toPrint
     */
    public void print(String toPrint) {
        System.out.print(toPrint);
    }

    /**
     * Print greeting
     *
     */
    public void printGreeting() {
        System.out.println("Hello from\n" + LOGO);
    }

    /**
     * Print string if input is unrecognised
     *
     */
    public void printUnrecognised() {
        System.out.println("----------------------------" +
                "----------------------------\n" +
                "OOPS!!! I'm sorry, but I don't know what that means :-(" + "\n"
                + "--------------------------------------------------------");
    }

    /**
     * Print string if input is exit command
     *
     */
    public static void printGoodbye() {
        System.out.println("--------------------------------" +
                "------------------------\n"
                + GOODBYE
                + "\n"
                + "--------------------------------------------------------");
    }

    public static String showLine() {
        return "--------------------------------------------------------";
    }
}
