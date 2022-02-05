package duke.ui;

import java.util.Scanner;

public class Ui {

    private static final String HYPHENATE = "    ____________________________________________________________\n";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    // BASIC COMMANDS
    public String returnBorderSpace() {
        return "    ";
    }

    public String returnUserSpace() {
        return "       ";
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println(returnBorderSpace()
                + "____________________________________________________________");
    }

    public String wrapsBorder(String toWrap) {
        return HYPHENATE + toWrap + HYPHENATE;
    }

    public void welcomeUser() {
        showLine();
        System.out.println(returnBorderSpace()
                + "Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?");
        showLine();
    }

    public String readCommand() {
        String command = "";
        System.out.println("");
        if (sc.hasNextLine()) {
            command = sc.nextLine().trim();
        }
        return command;
    }

    public void closePrinter() {
        sc.close();
    }

    public void showsLoadingError() {
        System.out.println(returnUserSpace() +
                "There seems to be an error loading your duke.task");
    }
}
