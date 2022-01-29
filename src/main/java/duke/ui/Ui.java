package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents the user interface that the user will interact with.
 */
public class Ui {
    private static final String LINE = "\t____________________________________________________________";
    private Scanner in;
    private PrintStream out;

    /**
     * Returns a new Ui Object with in initialized with java.util.Scanner(System.in) and out initialize with System.out
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Prints a hardcoded start up message to the console to indicate to the user that the program has started.
     */
    public void showStartUpMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.out.println("Hello I'm\n" + logo + "\n" + "What can I do for you?\n" + LINE);
    }

    /**
     * Prints any feedback message generated from commands given by the user with this.LINE to seperate the messages.
     */
    public void showFeedbackMessage(String mes) {
        if (mes == null) {
            return;
        }
        this.out.println(LINE + mes + LINE);
    }
    public String readCommand() {
        return in.nextLine().trim();
    }
}
