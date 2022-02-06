package duke;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private static final String LINE = "\n---------------------\n";
    private static final String LOGO = " ____         _   _     \n"
            + "|  _ \\       | | | |\n"
            + "| |_| |      | |-| |\n"
            + "| |_| |  _   | |-| |\n"
            + "|____/  |_|  |_| |_|\n";

    /**
     * Return a line
     * @return A String of line
     */
    public String getLine() {
        return LINE;
    }

    /**
     * Return the logo and greet user
     */
    public String greet() {
        return "Hello, I am B.H. How can I help you?\n" + LOGO + this.getLine();
    }

    /**
     * surround the input with two lines
     * @param input String to be surrounded
     * @return String of input surrounded with two lines
     */
    public static String echo(String input) {
        return LINE + input + LINE;
    }
}
