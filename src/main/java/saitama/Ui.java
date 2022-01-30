package saitama;

import java.util.Scanner;

public class Ui {
    /**
     * Shows the welcome logo and message.
     */
    public void showWelcome() {
        String logo = "   _____       _ _                        \n"
                + "  / ____|     (_) |                       \n"
                + " | (___   __ _ _| |_ __ _ _ __ ___   __ _ \n"
                + "  \\___ \\ / _` | | __/ _` | '_ ` _ \\ / _` |\n"
                + "  ____) | (_| | | || (_| | | | | | | (_| |\n"
                + " |_____/ \\__,_|_|\\__\\__,_|_| |_| |_|\\__,_|\n";

        System.out.println(logo);
        showLine();
        System.out.println("I'm Saitama, a hero for fun.");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the user input.
     *
     * @return The trimmed user input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    /**
     * Prints the error message.
     *
     * @param errorMessage The error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints the saving message.
     */
    public void showSave() {
        System.out.println("OK...");
        showLine();
        System.out.println("Saving...");
    }
}
