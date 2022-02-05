package duke.gui;
import java.util.Scanner;

/**
 * Ui class to handle interactions with User.
 */
public class Ui {

    private static Scanner sc;
    private static boolean firstUserChat;

    // variables for indentation
    private String divider = "______________________________________";
    private String indentationBase = "\t";
    private String indentationText = "\t  ";
    private String indentationTaskStatus = "\t    ";
    private String logo = indentationText
            + "    _                  _     \n" + indentationText
            + "   |_|                |_|    \n" + indentationText
            + "    _  __ _ _ ____   ___ ___ \n" + indentationText
            + "   | |/ _` | '__\\ \\ / / / __|\n" + indentationText
            + "   | | (_| | |   \\ V /| \\__ \\\n" + indentationText
            + "   | |\\__,_|_|    \\_/ |_|___/\n" + indentationText
            + "  _/ |                       \n" + indentationText
            + " |__/                        \n";
    private String emoji = "😀";
    private String userName;

    static {
        sc = new Scanner(System.in);
        firstUserChat = true;
    }

    /**
     * Basic constructor to initialize the Ui object.
     */
    public Ui() {}

    /**
     * Method to greet & ask for user's name.
     * @return String representing the user's name.
     */
    public String showWelcome() {

        // Jarvis introduces himself
        showText("Hello, I'm\n" + logo);
        showText("your personal assistant");
        showText("How should I address you?");

        // Jarvis asks for user's name
        this.userName = sc.nextLine();

        showText("Splendid! My pleasure to serve you " + userName);

        return this.userName;
    }

    /**
     * Method to bid goodbye to the user, when the user wants to exit Duke.
     */
    public String showGoodbye() {
        // Jarvis says good bye & the scanner is closed
        sc.close();
        return showText("Goodbye for now. \n");
    }

    /**
     * Method to read in command from user.
     * @return String representing user command.
     */
    public String readCommand() {
        showText("What " + (firstUserChat ? "" : "else ")
                + "may I assist you with today, " + this.userName + "? \n\t" + divider);
        firstUserChat = (firstUserChat == true) ? false : firstUserChat;
        String userCommand = sc.nextLine();
        showLine();
        return userCommand;
    }

    /**
     * Method to print a text to screen.
     * @param text String of text to be printed.
     */
    public String showText(String text) {
        return String.format("%s%s", indentationText, text);
    }

    /**
     * Method to print a task to screen.
     * @param task String of task to be printed.
     */
    public String showTask(String task) {
        return String.format("%s%s", indentationTaskStatus, task);
    }

    /**
     * Method to show a line on the screen.
     */
    public String showLine() {
        return String.format("%s%s", indentationBase, divider);
    }

    /**
     * Method to show an error on the screen.
     * @param errorMessage String of errorMessage to be printed.
     */
    public String showError(String errorMessage) {
        return String.format("%s%s", indentationText, errorMessage);
    }
}
