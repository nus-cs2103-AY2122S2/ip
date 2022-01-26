package ui;

import java.io.InputStream;
import java.io.PrintStream;

import java.util.Scanner;

/**
 * UI of the application.
 */
public class Ui {
    private static final String DIALOG_START = "Meep: ";
    private static final String DIVIDER = "____________________________________________________________";

    private final Scanner in;
    private final PrintStream out;


    /**
     * Constructor for class Ui.
     */
    public Ui() {
        this(System.in, System.out);
    }


    /**
     * Constructor for class Ui.
     *
     * @param in  InputStream.
     * @param out PrintStream.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Gets user command.
     *
     * @return the user command.
     */
    public String getUserCommand() {
        out.print("You: ");
        String fullInputLine = in.nextLine();


        return fullInputLine;
    }

    /**
     * Print logo.
     */
    public void printLogo() {
        print(DIVIDER, Messages.MESSAGE_LOGO, Messages.MESSAGE_HI, DIVIDER);
    }

    /**
     * Print bye.
     */
    public void printBye() {
        print(DIVIDER, Messages.MESSAGE_BYE, DIVIDER);
    }

    /**
     * Print msg without start dialog.
     *
     * @param message the message.
     */
    public void print(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }

    /**
     * Print msg with start dialog.
     *
     * @param message the message.
     */
    public void printMsg(String... message) {
        out.print(DIALOG_START);
        for (String m : message) {
            out.println(m);
        }
    }

}
