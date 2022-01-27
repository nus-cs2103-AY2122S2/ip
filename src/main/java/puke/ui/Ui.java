package puke.ui;

/**
 * Handles the user interface for the chat-bot.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";

    /**
     * Prints the logo and a welcome message.
     */
    public void printWelcomeMessage() {
        String logo = "   ____        _        \n"
                + "  |  _ \\ _   _| | _____ \n"
                + "  | |_| | | | | |/ / _ \\\n"
                + "  | |__/| |_| |   <  __/\n"
                + "  |_|    \\__,_|_|\\_\\___|\n";

        System.out.print(LINE + logo + "Hello! I'm Puke, your friendly neighbourhood chatbot!\n"
                + "What do you want to do?\n" + LINE);
    }

    /**
     * Prints the arrow head for user to type commands.
     */
    public void printCommandHead() {
        System.out.print("> ");
    }

    /**
     * Prints the response from the chat-bot.
     *
     * @param s Response from the chat-bot.
     */
    public void printResponse(String s) {
        System.out.print(s + "\n" + LINE);
    }

    /**
     * Prints the error message from the chat-bot.
     *
     * @param e Exception thrown from the chat-bot.
     */
    public void printError(Exception e) {
        System.out.print(e.getMessage() + "\n" + LINE);
    }

    /**
     * Prints the exit message.
     */
    public void printExit() {
        System.out.print("Alright bye. Come back to Puke anytime!\n" + LINE);
    }
}
