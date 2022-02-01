package nikki;

import nikki.task.Task;

public class Ui {
    /**
     * Color class to display colored text in the terminal
     */
    private class Color {
        public static final String NONE = "\033[m";

        public static final String GREEN = "\033[92m";
        public static final String RED = "\033[31m";
        public static final String PURPLE = "\033[35m";
        public static final String YELLOW = "\033[33m";
    }

    /**
     * Display banner containing title and subtitle
     */
    public void printBanner() {
        String logo = "███╗░░██╗██╗██╗░░██╗██╗░░██╗██╗　　　　 　∧_∧\n"
                    + "████╗░██║██║██║░██╔╝██║░██╔╝██║　　　　 ( ﾟωﾟ)\n"
                    + "██╔██╗██║██║█████═╝░█████═╝░██║　　　　 /ｕ ｕ\n"
                    + "██║╚████║██║██╔═██╗░██╔═██╗░██║　　　　/ / /\n"
                    + "██║░╚███║██║██║░╚██╗██║░╚██╗██║　　　 (ノノ\n"
                    + "╚═╝░░╚══╝╚═╝╚═╝░░╚═╝╚═╝░░╚═╝╚═╝　　　彡\n";

        String description = "Your Personal Assistant Chatbot that helps you keep track of the important things in life";

        System.out.println(Color.PURPLE + logo + Color.NONE);
        System.out.println(description);
    }

    /**
     * Method for Nikki to print message in a formatted style
     *
     * @param message message to print
     */
    public void say(String message) {
        // Set color theme for Nikki's text
        String defaultColor = Color.GREEN;
        System.out.println(defaultColor);

        System.out.println("<<<<<<<<");
        System.out.println(message);
        // Default back to green to allow different colored messages
        System.out.println(defaultColor + ">>>>>>>>");

        // Reset to default
        System.out.println(Color.NONE);
    }

    /**
     * Method for Nikki to print error messages
     *
     * @param message error message to print
     */
    public void error(String message) {
        say(Color.RED + message);
    }

    /**
     * Method for Nikki to print warning messages
     *
     * @param message warning message to print
     */
    public void warning(String message) {
        say(Color.YELLOW + message);
    }

    /**
     * Log the addition of tasks in the same format
     *
     * @param task task added to be logged
     */
    public void logNewTask(Task task, int taskCount) {
        say(String.format(
                "[+] Added following duke.task:\n" +
                        "\t%s\n" +
                        "Now you have %d tasks in the list.",
                task.nameWithStatus(), taskCount));
    }
}
