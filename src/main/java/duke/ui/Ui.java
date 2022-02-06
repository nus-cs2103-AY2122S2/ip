package duke.ui;

import java.util.ArrayList;

import duke.exception.ErrorMessage;
import duke.task.Task;

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
    private static final String GREETING = "Meow! I'm Duke!\nWhat can I do for you?\n"
            + "(Type 'help' to view the available commands.)";
    private static final String GOODBYE = "Bye. Meow!";
    private static final String MESSAGE_NUM_LIST_ITEMS = "%sNumber of tasks in list: %d";
    private static final String MESSAGE_LIST_ITEM = "%d. %s\n";
    private static final String MESSAGE_TASK_WITH_KEYWORD = "Here are the matching tasks with keyword: %s\n";

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
     * Prints an error when stored data cannot be loaded.
     */
    public void showLoadingError() {
        System.out.print(ErrorMessage.MESSAGE_LOADING_ERROR);
    }

    /**
     * Formats and returns the tasks to be shown to the user.
     * If a keyword is given, the keyword will be shown as well.
     *
     * @param listItems List of tasks to be shown.
     * @param keyword Keyword to be shown.
     * @return The formatted tasks string.
     */
    public String showTasks(ArrayList<Task> listItems, String keyword) {
        StringBuilder formatted = new StringBuilder();
        if (!keyword.isBlank()) {
            formatted.append(String.format(MESSAGE_TASK_WITH_KEYWORD, keyword));
        }
        for (int i = 0; i < listItems.size(); i++) {
            formatted.append(String.format(MESSAGE_LIST_ITEM, i + 1, listItems.get(i)));
        }
        return showMessage(String.format(MESSAGE_NUM_LIST_ITEMS, formatted, listItems.size()));
    }
}
