package duke.ui;

import duke.exception.ErrorMessage;
import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a UI that deals with interactions with the user.
 * */
public class Ui {
    private static final String CAT_FACE = " =^_^=\n";
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____      /\\_/\\           ___\n"
            + "| | | | | | | |/ / _      = o_o =_______    \\ \\  -Julie Rhodes-\\\n"
            + "| |_| | |_| |   <  __/     __^      __(  \\.__) )\n"
            + "|____/ \\__,_|_|\\_\\___| (@)<_____>__(_____)____/\n";
    private static final String GREETING = "Meow! I'm Duke!\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Meow!";
    private static final String MESSAGE_NUM_LIST_ITEMS = "Number of tasks in list: ";
    private static final String MESSAGE_TASK_WITH_KEYWORD = "Here are the matching tasks with keyword: ";

    public Ui() {
    }

    /**
     * Formats the message to be shown to the user.
     *
     * @param message The message to be shown.
     * @return The formatted message.
     */
    public String showMessage(String message) {
        return message + CAT_FACE;
    }

    /**
     * Returns the logo to be shown to the user.
     *
     * @return The logo.
     */
    public String showLogo() {
        return LOGO;
    }

    /**
     * Returns the greeting to be shown to the user.
     *
     * @return The greeting.
     */
    public String showGreeting() {
        return showMessage(GREETING);
    }

    /**
     * Returns the goodbye message to be shown to the user.
     *
     * @return The goodbye message.
     */
    public String showGoodbye() {
        return showMessage(GOODBYE);
    }

    /**
     * Prints an error when data cannot be loaded.
     */
    public void showLoadingError() {
        System.out.print(showMessage(ErrorMessage.MESSAGE_LOADING_ERROR));
    }

    /**
     * Formats and returns the tasks to be shown to the user.
     *
     * @param listItems ArrayList of tasks to be printed.
     * @return The formatted tasks string.
     */
    public String showAllTasks(ArrayList<Task> listItems) {
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < listItems.size(); i++) {
            formatted.append(i + 1).append(". ").append(listItems.get(i)).append("\n");
        }
        formatted.append(MESSAGE_NUM_LIST_ITEMS).append(listItems.size());
        return showMessage(formatted.toString());
    }

    /**
     * Formats and returns the tasks with a given keyword to be shown to the user.
     *
     * @param listItems ArrayList of tasks to be printed.
     * @param keyword Keyword to be searched for.
     * @return The formatted tasks string.
     */
    public String showFoundTasks(ArrayList<Task> listItems, String keyword) {
        StringBuilder formatted = new StringBuilder();
        formatted.append(MESSAGE_TASK_WITH_KEYWORD).append(keyword).append(" \n");
        for (int i = 0; i < listItems.size(); i++) {
            formatted.append(i + 1).append(". ").append(listItems.get(i)).append("\n");
        }
        formatted.append(MESSAGE_NUM_LIST_ITEMS).append(listItems.size());
        return showMessage(formatted.toString());
    }
}
