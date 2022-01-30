package duke.ui;
import java.util.Scanner;

/**
 * Ui class to handle interactions with User. 
 */
public class Ui {

    // variables for indentation 
    String divider = "______________________________________";
    String indentationBase = "\t";
    String indentationText = "\t  ";
    String indentationTaskStatus = "\t    ";
    String logo =   indentationText +
    "    _                  _     \n" + indentationText +
    "   |_|                |_|    \n" + indentationText +
    "    _  __ _ _ ____   ___ ___ \n" + indentationText +
    "   | |/ _` | '__\\ \\ / / / __|\n" + indentationText +
    "   | | (_| | |   \\ V /| \\__ \\\n" + indentationText +
    "   | |\\__,_|_|    \\_/ |_|___/\n" + indentationText +
    "  _/ |                       \n" + indentationText +
    " |__/                        \n"; 
    String emoji = "😀";  

    // variables for 
    private static Scanner sc;
    private static boolean firstUserChat;
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
    public void showGoodbye() {
        // Jarvis says good bye & the scanner is closed
        showText("Goodbye for now. \n");
        sc.close();
    }

    /**
     * Method to read in command from user. 
     * @return String representing user command. 
     */
    public String readCommand() {
        showText(String.format("What %smay I assist you with today, %s? \n\t%s", (firstUserChat ? "" : "else "), this.userName, divider));
        firstUserChat = (firstUserChat==true) ? false : firstUserChat;
        String userCommand = sc.nextLine();
        showLine();
        return userCommand;
    }

    /**
     * Method to print a text to screen. 
     * @param text String of text to be printed. 
     */
    public void showText(String text) {
        System.out.println(String.format("%s%s", indentationText, text));
    }

    /**
     * Method to print a task to screen. 
     * @param task String of task to be printed. 
     */
    public void showTask(String task) {
        System.out.println(String.format("%s%s", indentationTaskStatus, task));
    }

    /**
     * Method to show a line on the screen. 
     */
    public void showLine() {
        System.out.println(String.format("%s%s", indentationBase, divider));
    }

    /**
     * Method to show an error on the screen. 
     * @param errorMessage String of errorMessage to be printed. 
     */
    public void showError(String errorMessage) {
        System.err.println(String.format("%s%s", indentationText, errorMessage));
    }
    
}
