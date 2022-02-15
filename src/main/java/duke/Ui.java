package duke;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private static final String LINE = "\n------------------------------------------------\n";
    private static final String USER_LINE = "\n**************************************************\n";
    private static final String LOGO = " ____         _   _     \n"
            + "|  _ \\       | | | |\n"
            + "| |_| |      | |-| |\n"
            + "| |_| |  _   | |-| |\n"
            + "|____/  |_|  |_| |_|\n";

    /**
     * Returns a line
     *
     * @return A String of line
     */
    public String getLine() {
        return LINE;
    }

    /**
     * Returns the logo and greet user
     */
    public String greet() {
        return echo("Hello, I am B.H. How can I help you?");
    }

    /**
     * Returns goodbye message
     *
     * @return Goodbye message
     */
    public static String sayBye() {
        return echo("GoodBye! Thanks for using B.H!");
    }

    /**
     * Returns wrong input warning
     *
     * @return Wrong input warning
     */
    public String sayWrongInput() {
        return echo("Wrong input, please try again");
    }

    /**
     * Surrounds the input with two lines
     *
     * @param input String to be surrounded
     * @return String of input surrounded with two lines
     */
    public static String echo(String input) {
        return LINE + input + LINE;
    }


    /**
     * Surrounds the input with two lines of stars
     *
     * @param input String to be surrounded
     * @return String of input surrounded with two lines
     */
    public static String userEcho(String input) {
        return USER_LINE + input + USER_LINE;
    }
}
