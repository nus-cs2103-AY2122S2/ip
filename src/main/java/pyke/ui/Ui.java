package pyke.ui;
import java.util.Scanner;

public class Ui {
    private static final String GREETINGS = "Hello! I'm Pyke\n" + "What can I do for you?";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";
    private static final String HELP =
            "Command: todo [task name] \n \t Add a todo task into the list \n"
            + "Command: deadline [task name] \\by [deadline time] \n \t Add a deadline task into the list \n"
            + "Command: event [task name] \\at [event time] \n \t Add an event into the list \n"
            + "Command: list \n \t List all tasks \n"
            + "Command: mark [task id] \n \t Mark the task as done\n"
            + "Command: unmark [task id] \n \t Mark the task as undone\n"
            + "Command: find [keyword] \n \t Find all tasks which contain the keyword in name\n"
            + "Command: delete [task id] \n \t Delete that task\n"
            + "Command: bye \n \t Exit the chat bot";
    private Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Converts the plain output text to chat-box style with indentation for output.
     *
     * @param text : String (output text).
     * @return chat-box style output text : String.
     */
    private String convertToChatBox(String text) {
        /*
        return "    ____________________________________________________________\n"
                + text.replaceAll("(?m)^", "     ") + "\n"
                + "    ____________________________________________________________\n";

         */
        return text;
    }

    /**
     * Reads a line of String from users' input.
     *
     * @return the command in String.
     */
    public String getCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the duke logo.
     */
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(convertToChatBox(logo));
    }
    public void sayGreeting() {
        System.out.println(convertToChatBox(GREETINGS));
    }
    public void sayFarewell() {
        System.out.println(convertToChatBox(FAREWELL));
    }

    public void outputHelpInfo() {
        System.out.println(convertToChatBox(HELP));
    }
    /**
     * Outputs text in a chat-box style.
     *
     * @param text the original text for output.
     */
    public void outputText(String text) {
        System.out.println(convertToChatBox(text));
    }

    /**
     * Outputs exception information in a chat-box style.
     *
     * @param exceptionInfo the original info about exceptions.
     */
    public void outputException(String exceptionInfo) {
        System.out.println(convertToChatBox(exceptionInfo));
    }

    public String outputUiText(String text) {
        return text;
    }

    public String outputUiGreeting() {
        return GREETINGS;
    }

    public String outputUiFarewell() {
        return FAREWELL;
    }

    public String outputUiHelp() {
        return HELP;
    }
}
