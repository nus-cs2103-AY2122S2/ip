package duke.ui;

import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke, What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private static final String DIVIDER = "-------------------------------------------------";

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
        showMessage(WELCOME_MESSAGE);
    }

    public void showExitMessage() {
        showMessage(EXIT_MESSAGE);
        sc.close();
    }

    public String getUserInput() {
        System.out.print("~~>Enter command: ");
        return sc.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
}
