package main.java.ari.ui;

import java.util.Scanner;

public class Ui { // deals with interactions with the user
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

    public String getUserInput() {
        String input = sc.nextLine();
        printWithTab(LINE_BREAK);

        return input;
    }

    public void displayWelcomeMessage() {
        printWithTab(LINE_BREAK);
        System.out.println(LOGO);
        printWithTab(WELCOME_MESSAGE);
        printWithTab(WELCOME_QUESTION);
        printWithTab(LINE_BREAK);
        System.out.println();
    }

    public void displayMessage(String message) {
        printWithTab(message);
        printWithTab(LINE_BREAK);
        System.out.println();
    }

    private void printWithTab(String message) {
        String[] mesArray = message.split("\n");
        for (String mes : mesArray) {
            System.out.println("\t" + mes);
        }
    }
}
