package jarvis.utils;

import java.util.Scanner;

public class TextUI {
    private static final String LOGO = "\n"
        + "    /$$$$$  /$$$$$$  /$$$$$$$  /$$    /$$ /$$$$$$  /$$$$$$\n"
        + "   |__  $$ /$$__  $$| $$__  $$| $$   | $$|_  $$_/ /$$__  $$\n"
        + "      | $$| $$  \\ $$| $$  \\ $$| $$   | $$  | $$  | $$  \\__/\n"
        + "      | $$| $$$$$$$$| $$$$$$$/|  $$ / $$/  | $$  |  $$$$$$\n"
        + " /$$  | $$| $$__  $$| $$__  $$ \\  $$ $$/   | $$   \\____  $$\n"
        + "| $$  | $$| $$  | $$| $$  \\ $$  \\  $$$/    | $$   /$$  \\ $$\n"
        + "|  $$$$$$/| $$  | $$| $$  | $$   \\  $/    /$$$$$$|  $$$$$$/\n"
        + " \\______/ |__/  |__/|__/  |__/    \\_/    |______/ \\______/\n";
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String EXIT_MSG = "Bye. Hope to see you again soon!";
    private static final String GREET_MSG = "Hello! I'm Jarvis\nWhat can I do for you?";

    private final Scanner sc;

    public TextUI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Get the next command.
     *
     * @return the next command
     */
    public String nextCmd() {
        return sc.nextLine();
    }

    /**
     * Checks if there are still any Duke.commands.
     *
     * @return whether there is a next line
     */
    public boolean hasNextCmd() {
        return sc.hasNextLine();
    }

    /**
     * Formats and prints the given message.
     *
     * @param msg formatted message
     */
    public void printMsg(String msg) {
        System.out.println(SEPARATOR);
        System.out.println(msg);
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Get greeting message.
     * @return greet msg
     */
    public static String getGreetMsg() {
        return GREET_MSG;
    }

    /**
     * Prints the greeting message.
     */
    public void greet() {
        System.out.println(LOGO);
        printMsg(GREET_MSG);
    }

    public static String getGoodbyeMsg() {
        return EXIT_MSG;
    }
}
