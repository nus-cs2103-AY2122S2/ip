package ari.ui;

import java.util.Scanner;

/**
 * Deals with the interactions with the user
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Dear Master, I am Ari, your Personal Assistant Chatbot";
    private static final String WELCOME_QUESTION = "What can I do for you?";

    public Ui() {}

    public static String displayWelcomeMessage() {
        return WELCOME_MESSAGE + "\n" + WELCOME_QUESTION;
    }
}

