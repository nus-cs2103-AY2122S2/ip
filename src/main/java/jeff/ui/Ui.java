package jeff.ui;

import java.util.Scanner;

/**
 * Ui class is used to interact with the user.
 */
public class Ui {

    private static final String LOGO = "Hello! My name is \n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@      @@.         @@          @@          @@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@      @@.         @@          &@          @@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@      @@.     @@@@@@      @@&@@@     @@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@      @@.     @@@@@@      @@@@@@     @@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@      @@.        ,@@          @@          @@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@      @@.     @@@@@@      @@@@@@      @@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@      @@.     @@@@@@      @@@@@@      @@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@       .@@.         #@      @@&@@@      @@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@      .@@@.         #@      @@@@@@      @@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "What can I do for you?";

    private static final String DIVIDER = "________________________________________________________________________\n";

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

    /**
     * Display the goodbye message when requested.
     */
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

    public void showEmptyListFound() {
        System.out.println("I cannot find any such entry since your task list is currently empty.");
    }

    public void showNoneFound(String str) {
        System.out.println("Sorry but Jeff could not find any task matching the keyword: " + str + "\n");
    }

    public void showFoundList(String str) {
        System.out.println("Here are the matching tasks in your list:\n" + str);
    }

    public void showMark(String currTask) {
        System.out.println("Nice! I've marked this task as done:\n" + addPrefix(currTask));
    }

    public void showUnmark(String currTask) {
        System.out.println("OK, I've marked this task as not done yet:\n" + addPrefix(currTask));
    }

    /**
     * Display the confirmation message when adding a new task.
     *
     * @param currTask current task that was added.
     * @param size amount of tasks in the list.
     */
    public void showAdded(String currTask, int size) {
        System.out.println("Got it. I've added this task:\n"
                        + addPrefix(currTask) + "\n"
                        + "Now you have " + size + " task(s) in the list.");
    }

    /**
     * Display the confirmation message when deleting a task.
     *
     * @param currTask task that was deleted.
     * @param size amount of tasks left in the list.
     */
    public void showDelete(String currTask, int size) {
        System.out.println("Noted. I've removed this task:\n"
                + addPrefix(currTask) + "\n"
                + "Now you have " + size + " task(s) in the list.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Display a message showing users where to get help.
     */
    public void showHelp() {
        System.out.println("I do not understand your commands\n"
                + "Please refer to the readme.txt for the available commands\n");
    }
}
