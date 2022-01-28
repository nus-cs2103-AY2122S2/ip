package main.java.ari.ui;

import java.util.Scanner;

/**
 * Deals with the interactions with the user
 */
public class Ui {
    private Scanner sc;

    private static final String LINE_BREAK = "____________________________________________________________";
    private static final String LOGO = "             ___      .______       __         \n"
            + "            /   \\     |   _  \\     |  |      \n"
            + "           /  ^  \\    |  |_)  |    |  |      \n"
            + "          /  /_\\  \\   |      /     |  |      \n"
            + "         /  _____  \\  |  |\\  \\----.|  |      \n"
            + "        /__/     \\__\\ | _| `._____||__|      \n"
            + "\n";
    private static final String WELCOME_MESSAGE = "Dear Master, I am Ari, your Personal Assistant Chatbot";
    private static final String WELCOME_QUESTION = "What can I do for you?";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns user input
     *
     * @return user input
     */
    public String getUserInput() {
        String input = sc.nextLine();
        printWithTab(LINE_BREAK);

        return input;
    }

    /**
     * Prints welcome message
     */
    public void displayWelcomeMessage() {
        printWithTab(LINE_BREAK);
        System.out.println(LOGO);
        printWithTab(WELCOME_MESSAGE);
        printWithTab(WELCOME_QUESTION);
        printWithTab(LINE_BREAK);
        System.out.println();
    }

    /**
     * Prints message that other classes want to display to user
     *
     * @param message message to display to user
     */
    public void displayMessage(String message) {
        printWithTab(message);
        printWithTab(LINE_BREAK);
        System.out.println();
    }

    /**
     * Prints the message with tab prepended to it
     *
     * @param message message to pad with tab
     */
    private void printWithTab(String message) {
        String[] mesArray = message.split("\n");
        for (String mes : mesArray) {
            System.out.println("\t" + mes);
        }
    }
}
