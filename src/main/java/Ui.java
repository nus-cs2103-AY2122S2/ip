import java.util.Scanner;

/**
 * Ui class is used to interact with the user.
 */
public class Ui {

    private static final String LOGO = "Hello! My name is \n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@      @@.         @@          @@          @@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@      @@.         @@          &@          @@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@      @@.     @@@@@@      @@&@@@     @@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@      @@.     @@@@@@      @@@@@@     @@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@      @@.        ,@@          @@          @@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@      @@.     @@@@@@      @@@@@@      @@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@      @@.     @@@@@@      @@@@@@      @@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@       .@@.         #@      @@&@@@      @@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@      .@@@.         #@      @@@@@@      @@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "What can I do for you?";

    private static final String DIVIDER = "---------------------------------------------------------------------------\n";

    private static final String PREFIX = "    ";

    private final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(LOGO);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public static String addPrefix(String str) {
        return PREFIX + str;
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showBye() {
        System.out.println("Bye, my name is Jeff");
        sc.close();
    }

    public void showList(String str) {
        System.out.println(str);
    }

    public void showEmptyList() {
        System.out.println("Your task list is currently empty.");
    }

    public void showMark(String currTask){
        System.out.println("Nice! I've marked this task as done:\n  " + addPrefix(currTask));
    }

    public void showUnmark(String currTask) {
        System.out.println("OK, I've marked this task as not done yet:\n  " + addPrefix(currTask));
    }

    public void showAdded(String currTask, int size) {
        System.out.println("Got it. I've added this task:\n"
                        + addPrefix(currTask) + "\n"
                        + "Now you have " + size + " task(s) in the list.");
    }

    public void showDelete(String currTask, int size) {
        System.out.println("Noted. I've removed this task:\n"
                + addPrefix(currTask) + "\n"
                + "Now you have " + size + " task(s) in the list.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showHelp() {
        System.out.println("I do not understand your commands\n"
                + "Please refer to the readme.txt for the available commands\n");
    }
}
