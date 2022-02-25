package duke.ui;

import java.util.Scanner;

public class Ui {

    private static final String INDENT = "     ";
    private static final String LINE = "    ____________________________________________________________";

    public void showLine() {
        System.out.println(LINE);
    }

    public void sandwich(String str) {
        showLine();
        System.out.println(INDENT+str);
        showLine();
        System.out.println();
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(INDENT + message);
    }

    public void showWelcome() {
        sandwich("Hello! I'm Duke\n" + INDENT + "What can I do for you?");
    }

    public void showFarewell() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        showMessage("Whoops! Error loading tasks.");
        showMessage("Try clearing tasks.txt and trying again?");
        showMessage("xoxo");
    }

    public void showError(String errorMessage) {
        showMessage("ERROR: " + errorMessage);
    }
}
