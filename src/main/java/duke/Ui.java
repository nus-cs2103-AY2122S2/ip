package duke;

/**
 * Deals with interactions with the user
 */
public class Ui {
    static String line = "\n---------------------\n";
    static String logo = " ____         _   _     \n"
            + "|  _ \\       | | | |\n"
            + "| |_| |      | |-| |\n"
            + "| |_| |  _   | |-| |\n"
            + "|____/  |_|  |_| |_|\n";

    /**
     * Return a line
     * @return A String of line
     */
    public String getLine() {
        return line;
    }

    /**
     * Return the logo and greet user
     */
    public String greet() {
        return "Hello, I am B.H. How can I help you?\n" + logo + this.getLine();
    }
}
