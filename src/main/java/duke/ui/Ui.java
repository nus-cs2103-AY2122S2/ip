package duke.ui;

import duke.exception.ErrorMessage;
import duke.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

// deals with interactions with the user
public class Ui {
    private static final String LINE_BREAK
            = "____________________________________________________________\n";
    private static final String CAT_FACE = " =^_^=\n";
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____      /\\_/\\           ___\n"
            + "| | | | | | | |/ / _      = o_o =_______    \\ \\  -Julie Rhodes-\\\n"
            + "| |_| | |_| |   <  __/     __^      __(  \\.__) )\n"
            + "|____/ \\__,_|_|\\_\\___| (@)<_____>__(_____)____/\n";
    private static final String GREETING = "Meow! I'm Duke!\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Meow!";
    private static final String MESSAGE_INDEXED_LIST_ITEM = "%1$d. %2$s";
    private static final String MESSAGE_NUM_LIST_ITEMS = "Number of tasks in list: ";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getInput() {
        return in.nextLine().strip();
    }

    /**
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        out.print(LINE_BREAK + message + CAT_FACE + LINE_BREAK);
    }

    /**
     * Prints the greeting and logo.
     */
    public void printGreeting() {
        out.print(LOGO);
        printMessage(GREETING);
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbye() {
        printMessage(GOODBYE);
    }

    public void printList(ArrayList<Task> listItems) {
        StringBuilder formatted = new StringBuilder();
        int displayIndex = 1;
        for (Task listItem : listItems) {
            formatted.append(formatListItem(displayIndex, listItem.toString())).append("\n");
            displayIndex++;
        }
        formatted.append(MESSAGE_NUM_LIST_ITEMS).append(listItems.size());
        printMessage(formatted.toString());
    }

    private String formatListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

    public void showLoadingError() {
        printMessage(ErrorMessage.MESSAGE_LOADING_ERROR);
    }
}
